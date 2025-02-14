package com.demo.api.downloadPDF.util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SetWordTemplate {
    private final static String[] FONT_PATHS = {"fonts/Songti.ttc"};

    public static byte[] set(ParamTemplate paramTemplate) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(paramTemplate.getParams());
        LoopRowTableRenderPolicy hackLoopTableRenderPolicy = new LoopRowTableRenderPolicy();
        // 动态添加list
        ConfigureBuilder configureBuilder = Configure.builder();
        if (paramTemplate.getListParams() != null && !paramTemplate.getListParams().isEmpty()) {
            paramTemplate.getListParams().forEach((k, v) -> {
                configureBuilder.bind(k, hackLoopTableRenderPolicy);
            });
            params.putAll(paramTemplate.getListParams());
        }
        Configure config = configureBuilder.build();
        if (paramTemplate.getImageTemplates() != null && !paramTemplate.getImageTemplates().isEmpty()) {
            paramTemplate.getImageTemplates().forEach((k, v) -> {
                PictureRenderData pictureRenderData = new PictureRenderData(
                        v.getWidth(), v.getHeight(), v.getPictureType(), v.getInput());
                params.put(k, pictureRenderData);
            });
        }
        String path = copyTempFile(paramTemplate.getTemplatePath());
        XWPFTemplate template = XWPFTemplate.compile(path, config).render(params);
        try (FileOutputStream fos = new FileOutputStream(path)) {
            // 直接将模板内容写入文件
            template.write(fos);
            // 确保写入完毕后再读取文件内容
            fos.flush();  // 确保写入到文件系统
            if(!"pdf".equals(paramTemplate.getResultType())){
                return setResult(path);
            }
            //转pdf导出
            String fontPath = "";
            if (CollectionUtils.isEmpty(paramTemplate.getFontsPath())) {
                fontPath = copyTempFileFont(FONT_PATHS);
            } else {
                fontPath = copyTempFileFont(paramTemplate.getFontsPath().toArray(new String[0]));
            }
            String pdfPath = FileUtils.getTempDirectoryPath() + System.currentTimeMillis() + ".pdf";
            AsposeUtil.wordToPdf(path,pdfPath,fontPath);
            return setResult(pdfPath);
        } catch (Exception e) {
            throw new RuntimeException("文件流写入失败", e);
        } finally {
            // 删除临时文件
            File copyFile = new File(path);
            if (copyFile.exists()) {
                copyFile.delete();
            }
            // 关闭模板
            try {
                template.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭模板时发生错误", e);
            }
        }
    }

    private static byte[] setResult(String path) {
        try (FileInputStream fis = new FileInputStream(path);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray(); // 返回文件的字节数组
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将项目中的模板文件拷贝到根目录下
     * @return
     */
    private static String copyTempFile(String templeFilePath) {
        try (InputStream inputStream = SetWordTemplate.class.getClassLoader().getResourceAsStream(templeFilePath)){
            File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".docx");
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            return tempFile.getPath();
        } catch (IOException e) {
            log.error("模板转换失败:{}",e.getMessage());
            throw new RuntimeException("模板转换失败");
        }
    }

    /**
     * 将项目中的模板文件拷贝
     * @return
     */
    private static String copyTempFileFont(String... fontPath) {
        String tempDir = System.getProperty("java.io.tmpdir");
        File fontDir = new File(tempDir, "fonts");
        if (!fontDir.exists()) {
            fontDir.mkdirs(); // 创建目录
        }
        for(int i = 0; i < fontPath.length; i++){
            File tempFile = new File(fontDir, new File(fontPath[i]).getName());
            if (!tempFile.exists()) {
                try (InputStream inputStream = SetWordTemplate.class.getClassLoader().getResourceAsStream(fontPath[i])){
                    FileUtils.copyInputStreamToFile(inputStream, tempFile);
                } catch (IOException e) {
                    throw new RuntimeException("字体文件转换失败，请稍候重试");
                }
            }
        }
        return fontDir.getPath() + "/";
    }
}
