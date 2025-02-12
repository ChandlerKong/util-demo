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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SetWordTemplate {
    private final static String[] FONT_PATHS = {"fonts/Songti.ttc"};

    public byte[] set(ParamTemplate paramTemplate) {
        String fontPath = "";
        if (CollectionUtils.isEmpty(paramTemplate.getFontsPath())) {
            fontPath = copyTempFileFont(FONT_PATHS);
        } else {
            fontPath = copyTempFileFont(paramTemplate.getFontsPath().toArray(new String[0]));
        }
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
        try {
            try (FileOutputStream fos = new FileOutputStream(path);
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 XWPFTemplate template = XWPFTemplate.compile(path, config).render(params)) {
                template.write(fos);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                return byteArray; // 返回文件的字节数组
            } catch (IOException e) {
                log.error("数据写入失败:{}", e.getMessage());
                throw new RuntimeException("招标数据写入失败");
            }
        } finally {
            File copyFile = new File(path);
            if (copyFile.exists()) {
                copyFile.delete();
            }
        }
    }

    /**
     * 将项目中的模板文件拷贝到根目录下
     * @return
     */
    private String copyTempFile(String templeFilePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(templeFilePath)){
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
    private String copyTempFileFont(String... fontPath) {
        String tempDir = System.getProperty("java.io.tmpdir");
        File fontDir = new File(tempDir, "fonts");
        if (!fontDir.exists()) {
            fontDir.mkdirs(); // 创建目录
        }
        for(int i = 0; i < fontPath.length; i++){
            File tempFile = new File(fontDir, new File(fontPath[i]).getName());
            if (!tempFile.exists()) {
                try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fontPath[i])){
                    FileUtils.copyInputStreamToFile(inputStream, tempFile);
                } catch (IOException e) {
                    throw new RuntimeException("字体文件转换失败，请稍候重试");
                }
            }
        }
        return fontDir.getPath() + "/";
    }
}
