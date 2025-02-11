package com.demo.api.downloadPDF.service;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.demo.api.downloadPDF.util.AsposeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
public class DownLoadPDFService {
    private final static String DOCS_PATH = "docs/车队下载电子对账单.docx";
    private final static String[] FONT_PATHS = {"fonts/Songti.ttc"};

    @Data
    class Tmp1{
        private int num;
        private String no;
        private String address;

        public Tmp1(int num, String no, String address) {
            this.num = num;
            this.no = no;
            this.address = address;
        }
    }

    public void download(HttpServletResponse response) throws IOException {
        /**
         * 数据集
         * 字体文件 Map list 图片
         * pdf word
         * 文件名
         */
        //创建文档
        Map<String,Object> params = new HashMap<>();
        params.put("title","营口群利运输有限公司12月对账单（自动生成）管理费(120.00元)");
        params.put("name","营口群利运输有限公司");
        params.put("addressAndPhone","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）/0417-3217468");
        List<Tmp1> tmp1s = new ArrayList<>();
        tmp1s.add(new Tmp1(1,"1","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        tmp1s.add(new Tmp1(2,"2","辽宁省营口市鲅鱼圈区盐场09-粮贸大厦主楼（宏基商务宾馆1209号房）"));
        params.put("list1", tmp1s);

        LoopRowTableRenderPolicy hackLoopTableRenderPolicy = new LoopRowTableRenderPolicy();
        // 动态添加其他绑定
        ConfigureBuilder c = Configure.builder();
        c.bind("list1", hackLoopTableRenderPolicy);
        c.bind("list2", hackLoopTableRenderPolicy);
        Configure config = c.build();

        // 图片路径
        String imagePath = "/Users/kongxsh/Downloads/1.png"; // 你的图片路径
        // 1. 创建 PictureRenderData 来渲染图片，指定图片宽度和高度
        PictureRenderData pictureRenderData = new PictureRenderData(85, 85, imagePath);
        params.put("image1",pictureRenderData);

        // 2. 将图片数据添加到模板参数

        String path = copyTempFile(DOCS_PATH);

        XWPFTemplate template = XWPFTemplate.compile(path, config).render(params);
        try (FileOutputStream fos = new FileOutputStream(path)) {
            template.write(fos);
            fos.flush();
        } catch (IOException e) {
            log.error("数据写入失败:{}",e.getMessage());
            throw new RuntimeException("招标数据写入失败");
        }finally {
            try {
                template.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String pathFonts = copyTempFileFont(FONT_PATHS);
        String pdfPath = FileUtils.getTempDirectoryPath() + System.currentTimeMillis() + ".pdf";
        try {
            AsposeUtil.wordToPdf(path,pdfPath,pathFonts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 5. 提供前端下载
        String fileName = "招标协议书" + System.currentTimeMillis() + ".pdf";
        down(response, pdfPath, fileName);

    }

    private static String encodeImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] imageBytes = new byte[(int) file.length()];
        fileInputStream.read(imageBytes);
        fileInputStream.close();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private void down(HttpServletResponse response, String filePath, String realFileName) {
        String percentEncodedFileName = null;
        try {
            percentEncodedFileName = percentEncode(realFileName);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=").append(percentEncodedFileName).append(";").append("filename*=").append("utf-8''").append(percentEncodedFileName);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,download-filename");
        response.setHeader("Content-disposition", contentDispositionValue.toString());
        response.setHeader("download-filename", percentEncodedFileName);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
             // 输出流
             BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = bis.read(buff)) > 0) {
                bos.write(buff, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            log.error("下载模板转换失败:{}",e.getMessage());
            throw new RuntimeException("下载模板转换失败");
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

    /**
     * 百分号编码工具方法
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    public static String percentEncode(String s) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }
}
