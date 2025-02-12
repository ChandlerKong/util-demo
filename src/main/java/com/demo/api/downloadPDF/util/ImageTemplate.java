package com.demo.api.downloadPDF.util;

import com.deepoove.poi.data.PictureType;
import lombok.Data;

import java.io.InputStream;

@Data
public class ImageTemplate {
    private int width;
    private int height;
    private InputStream input;
    private PictureType pictureType;

    public ImageTemplate(int width, int height, InputStream input) {
        this.width = width;
        this.height = height;
        this.input = input;
    }
}
