package com.demo.api.downloadPDF.service;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.demo.api.downloadPDF.util.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
public class DownLoadPDFService {
    private final static String DOCS_PATH = "docs/word_demo.docx";
    private final static String[] FONT_PATHS = {"fonts/Songti.ttc"};

    public void downloadDemo(HttpServletResponse response) throws IOException {
        Map<String,String> params = new HashMap<>();
        params.put("text","测试文字");
        Map<String,List<Object>> listParams = new HashMap<>();
        List<Object> list = new ArrayList<>();
        for (int i = 0; i< 100; i++){
            Map<String,String> obj = new HashMap<>();
            obj.put("a","a" + "--" + i);
            obj.put("b","b" + "--" + i);
            obj.put("c","c" + "--" + i);
            obj.put("d","d" + "--" + i);
            list.add(obj);
        }
        listParams.put("list",list);
        RandomImageGenerator generator = new RandomImageGenerator();
        BufferedImage image = generator.generateRandomImage(200, 200);  // 创建一张500x500的图片
        // 使用 ByteArrayOutputStream 将 BufferedImage 写入
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 将 BufferedImage 转换为字节数组
        ImageIO.write(image, "png", byteArrayOutputStream);
        // 将字节数组转换为 InputStream
        Map<String, ImageTemplate> imageTemplates = new HashMap<>();
        imageTemplates.put("image",new ImageTemplate(50,50,
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));

        byte[] bytes = SetWordTemplate.set(new ParamTemplate(
                        DOCS_PATH, Arrays.asList(FONT_PATHS), params, listParams, imageTemplates));
        // 3. 输出到 Response
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=tmp.docx");
        response.setContentLength(bytes.length);
        try (OutputStream os = response.getOutputStream()) {
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
