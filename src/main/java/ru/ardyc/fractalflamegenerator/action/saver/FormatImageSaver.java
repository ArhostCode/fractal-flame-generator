package ru.ardyc.fractalflamegenerator.action.saver;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.nio.file.Path;

@RequiredArgsConstructor
public class FormatImageSaver implements ImageSaver {

    private final ImageFormat format;

    @SneakyThrows
    @Override
    public void save(FractalImage image, Path path) {
        ImageIO.write(ImageUtils.convertFractalImageToBufferedImage(image), format.getFormatName(), path.toFile());
    }
}
