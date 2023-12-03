package ru.ardyc.fractalflamegenerator.action.saver;

import ru.ardyc.fractalflamegenerator.model.image.FractalImage;

import java.nio.file.Path;

public interface ImageSaver {
    void save(FractalImage image, Path path);
}
