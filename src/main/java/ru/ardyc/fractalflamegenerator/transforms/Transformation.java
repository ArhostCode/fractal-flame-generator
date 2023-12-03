package ru.ardyc.fractalflamegenerator.transforms;


import ru.ardyc.fractalflamegenerator.model.world.Point;

import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
}
