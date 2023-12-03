package ru.ardyc.fractalflamegenerator.action.processor;

import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.image.Pixel;

public class LogGammaCorrectionImageProcessor implements ImageProcessor {
    private static final double GAMMA = 1.5;

    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        double gamma = GAMMA;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                if (image.pixel(x, y).getHitCount() != 0) {
                    image.pixel(x, y).setNormal(Math.log10(image.pixel(x, y).getHitCount()));
                    if (image.pixel(x, y).getNormal() > max) {
                        max = image.pixel(x, y).getNormal();
                    }
                }
            }
        }
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.setNormal(image.pixel(x, y).getNormal() / max);
                pixel.setRed((int) (pixel.getRed() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                pixel.setGreen((int) (pixel.getGreen() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                pixel.setBlue((int) (pixel.getBlue() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
            }
        }

    }
}
