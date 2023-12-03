package ru.ardyc.fractalflamegenerator.transforms;

import ru.ardyc.fractalflamegenerator.model.world.Point;

public class ExponentialTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double newX = Math.exp(x - 1) * Math.cos(Math.PI * y);
        double newY = Math.exp(x - 1) * Math.sin(Math.PI * y);
        return new Point(newX, newY);
    }
}
