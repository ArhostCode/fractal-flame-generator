package ru.ardyc.fractalflamegenerator.action.processor;

import ru.ardyc.fractalflamegenerator.model.image.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
