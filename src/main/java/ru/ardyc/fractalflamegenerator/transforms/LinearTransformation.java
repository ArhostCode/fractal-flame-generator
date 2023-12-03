package ru.ardyc.fractalflamegenerator.transforms;

import ru.ardyc.fractalflamegenerator.model.world.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return point;
    }
}
