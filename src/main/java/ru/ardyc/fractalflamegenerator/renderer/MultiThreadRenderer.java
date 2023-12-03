package ru.ardyc.fractalflamegenerator.renderer;

import lombok.SneakyThrows;
import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.world.Rect;
import ru.ardyc.fractalflamegenerator.transforms.AffineTransformation;
import ru.ardyc.fractalflamegenerator.transforms.Transformation;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MultiThreadRenderer extends AbstractRenderer {

    public MultiThreadRenderer(
            int affineCount,
            int samples,
            int iterPerSample,
            int symmetry,
            List<Transformation> variations
    ) {
        super(affineCount, samples, iterPerSample, symmetry, variations);
    }

    @SneakyThrows
    @Override
    public void renderAllImage(
            FractalImage image,
            Rect world,
            List<AffineTransformation> affineTransformations
    ) {
        var executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < samples; i++) {
            executorService.execute(
                    () -> renderOneSample(image, world, affineTransformations)
            );
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }

}
