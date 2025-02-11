package com.demo.api.downloadPDF.util;

import com.aspose.words.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * description : Aspose工具类
 */
@Slf4j
public class AsposeUtil {

    /**
     * 加载license 用于破解 不生成水印
     */
    @SneakyThrows
    private static void getLicense() {
        try (InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("lib/License.xml")) {
            License license = new License();
            license.setLicense(is);
        }
    }

    /**
     * word转pdf
     *
     * @param wordPath word文件保存的路径
     * @param pdfPath  转换后pdf文件保存的路径
     */
    public static void wordToPdf(String wordPath, String pdfPath, String fontPath) throws Exception {
        // 获取许可证
        getLicense();
        // 设置字体文件夹
        FontSettings.setFontsFolder(fontPath,false);
        // 加载 Word 文档
        Document doc = new Document(wordPath);
        // 设置 PdfSaveOptions
        PdfSaveOptions options = new PdfSaveOptions();
        options.setSaveFormat(SaveFormat.PDF);
        // 保存为 PDF
        try (FileOutputStream os = new FileOutputStream(new File(pdfPath))) {
            doc.save(os, options);
        }
    }
}
