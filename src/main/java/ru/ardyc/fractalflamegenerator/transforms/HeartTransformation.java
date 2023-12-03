package ru.ardyc.fractalflamegenerator.transforms;

import ru.ardyc.fractalflamegenerator.model.world.Point;

public class HeartTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double sqrt = Math.sqrt(x * x + y * y);
        double a = sqrt * Math.atan(y / x);
        double newX = sqrt * Math.sin(a);
        double newY = -sqrt * Math.cos(a);
        return new Point(newX, newY);
    }
}
