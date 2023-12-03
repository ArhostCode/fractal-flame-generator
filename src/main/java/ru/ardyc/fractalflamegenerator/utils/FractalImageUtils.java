package ru.ardyc.fractalflamegenerator.utils;

import lombok.experimental.UtilityClass;
import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.image.Pixel;
import ru.ardyc.fractalflamegenerator.model.world.Point;
import ru.ardyc.fractalflamegenerator.model.world.Rect;


@UtilityClass
public class FractalImageUtils {

    public static Pixel resolvePixel(Rect rect, Point point, FractalImage image) {
        if (!rect.contains(point)) {
            return null;
        }
        return image.pixel(
            (int) ((point.x() - rect.x()) / rect.width() * image.width()),
            (int) ((point.y() - rect.y()) / rect.height() * image.height())
        );
    }

}
