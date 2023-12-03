package ru.ardyc.fractalflamegenerator.renderer;

import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.world.Rect;
import ru.ardyc.fractalflamegenerator.transforms.AffineTransformation;
import ru.ardyc.fractalflamegenerator.transforms.Transformation;

import java.util.List;

public class OneThreadRenderer extends AbstractRenderer {

    public OneThreadRenderer(
        int affineCount,
        int samples,
        int iterPerSample,
        int symmetry,
        List<Transformation> variations
    ) {
        super(affineCount, samples, iterPerSample, symmetry, variations);
    }

    @Override
    public void renderAllImage(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) {
        for (int i = 0; i < samples; i++) {
            renderOneSample(image, world, affineTransformations);
        }
    }
}
