package ru.ardyc.fractalflamegenerator;

import ru.ardyc.fractalflamegenerator.action.processor.ImageProcessor;
import ru.ardyc.fractalflamegenerator.action.processor.LogGammaCorrectionImageProcessor;
import ru.ardyc.fractalflamegenerator.action.saver.FormatImageSaver;
import ru.ardyc.fractalflamegenerator.action.saver.ImageFormat;
import ru.ardyc.fractalflamegenerator.action.saver.ImageSaver;
import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.world.Rect;
import ru.ardyc.fractalflamegenerator.renderer.MultiThreadRenderer;
import ru.ardyc.fractalflamegenerator.renderer.Renderer;
import ru.ardyc.fractalflamegenerator.transforms.HeartTransformation;

import java.nio.file.Path;
import java.util.List;

public final class FractalFlameGenerator {

    private FractalFlameGenerator() {
    }

    public static FractalImage generate(
            int width,
            int height,
            Rect area,
            Renderer renderer,
            List<ImageProcessor> processors
    ) {
        FractalImage image = renderer.render(width, height, area);
        for (ImageProcessor processor : processors) {
            processor.process(image);
        }
        return image;
    }

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
    public static void main(String[] args) {
        FractalImage image = generate(
                2000,
                1000,
                new Rect(-4, -3, 8, 6),
                new MultiThreadRenderer(
                        5, 5, 100_000_000,
                        3, List.of(new HeartTransformation())
                ),
                List.of(new LogGammaCorrectionImageProcessor())
        );
        ImageSaver saver = new FormatImageSaver(ImageFormat.PNG);
        saver.save(image, Path.of("image.png"));
    }
}
