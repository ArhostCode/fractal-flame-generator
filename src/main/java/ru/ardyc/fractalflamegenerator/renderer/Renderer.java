package ru.ardyc.fractalflamegenerator.renderer;

import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.world.Rect;

@FunctionalInterface
public interface Renderer {
    FractalImage render(int width, int height, Rect world);
}
