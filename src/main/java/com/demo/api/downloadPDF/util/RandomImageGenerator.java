package com.demo.api.downloadPDF.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class RandomImageGenerator {

    public static void main(String[] args) {
        RandomImageGenerator generator = new RandomImageGenerator();
        try {
            BufferedImage image = generator.generateRandomImage(500, 500);  // 创建一张500x500的图片
            ImageIO.write(image, "png", new File("random_image.png"));  // 保存为PNG图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage generateRandomImage(int width, int height) {
        // 创建一个指定大小的空白图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // 设置背景色
        g2d.setColor(getRandomColor());
        g2d.fillRect(0, 0, width, height);

        // 绘制一些随机图形
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            g2d.setColor(getRandomColor());
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int w = random.nextInt(100);
            int h = random.nextInt(100);
            g2d.fillOval(x, y, w, h);  // 随机绘制圆形
        }

        // 绘制一些随机文本
        g2d.setColor(getRandomColor());
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));
        g2d.drawString("Random Text", random.nextInt(width - 100), random.nextInt(height - 50));

        // 释放资源
        g2d.dispose();
        return image;
    }

    // 获取随机颜色
    private Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}