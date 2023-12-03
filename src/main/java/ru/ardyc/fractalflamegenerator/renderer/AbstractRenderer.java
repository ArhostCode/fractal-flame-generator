package ru.ardyc.fractalflamegenerator.renderer;


import lombok.AllArgsConstructor;
import ru.ardyc.fractalflamegenerator.model.AffineCoefficient;
import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.image.Pixel;
import ru.ardyc.fractalflamegenerator.model.world.Point;
import ru.ardyc.fractalflamegenerator.model.world.Rect;
import ru.ardyc.fractalflamegenerator.transforms.AffineTransformation;
import ru.ardyc.fractalflamegenerator.transforms.Transformation;
import ru.ardyc.fractalflamegenerator.utils.FractalImageUtils;
import ru.ardyc.fractalflamegenerator.utils.ListUtils;
import ru.ardyc.fractalflamegenerator.utils.RectUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public abstract class AbstractRenderer implements Renderer {

    private static final int STEPS_FOR_NORMALIZATION = 20;

    protected final int affineCount;
    protected final int samples;
    protected final int iterPerSample;
    protected final int symmetry;
    protected final List<Transformation> variations;

    public FractalImage render(int width, int height, Rect world) {
        FractalImage image = FractalImage.create(width, height);
        List<AffineTransformation> affineTransformations = generateAffineTransformations();
        renderAllImage(image, world, affineTransformations);
        return image;
    }

    public abstract void renderAllImage(
            FractalImage image,
            Rect world,
            List<AffineTransformation> affineTransformations
    );

    private List<AffineTransformation> generateAffineTransformations() {
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        for (int i = 0; i < affineCount; i++) {
            AffineTransformation transformation =
                    new AffineTransformation(AffineCoefficient.generateRandom(ThreadLocalRandom.current()));
            affineTransformations.add(transformation);
        }
        return affineTransformations;
    }

    protected void renderOneSample(FractalImage image, Rect world, List<AffineTransformation> affineTransformations) {
        Point currentPoint = RectUtils.randomPoint(world);
        for (int step = -STEPS_FOR_NORMALIZATION; step < iterPerSample; ++step) {
            AffineTransformation affine = ListUtils.random(affineTransformations);
            Transformation variation = ListUtils.random(variations);
            currentPoint = affine.apply(currentPoint);
            currentPoint = variation.apply(currentPoint);
            if (step > 0) {
                double theta = 0.0;
                for (int chunk = 0; chunk < symmetry; theta += Math.PI * 2 / symmetry, ++chunk) {
                    var point = RectUtils.rotatePoint(world, currentPoint, theta);
                    processPoint(world, image, point, affine);
                }
            }
        }
    }

    private void processPoint(Rect world, FractalImage image, Point point, AffineTransformation affine) {
        if (!world.contains(point)) {
            return;
        }
        Pixel pixel = FractalImageUtils.resolvePixel(world, point, image);
        if (pixel != null) {
            synchronized (pixel) {
                Color color = affine.affineCoefficient().color();
                pixel.saturateHitCount(color);
            }
        }
    }
}
