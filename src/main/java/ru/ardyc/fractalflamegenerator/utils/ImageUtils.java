package ru.ardyc.fractalflamegenerator.utils;

import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.image.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage convertFractalImageToBufferedImage(FractalImage image) {
        BufferedImage renderedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
                renderedImage.setRGB(x, y, color.getRGB());
            }
        }
        return renderedImage;
    }
}
