package ru.ardyc.fractalflamegenerator.transforms;

import ru.ardyc.fractalflamegenerator.model.world.Point;

public class SphereTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x() / (point.x() * point.x() + point.y() * point.y());
        double y = point.y() / (point.x() * point.x() + point.y() * point.y());
        return new Point(x, y);
    }
}
