package ru.ardyc.fractalflamegenerator.controller;

import jakarta.ws.rs.*;
import ru.ardyc.fractalflamegenerator.FractalFlameGenerator;
import ru.ardyc.fractalflamegenerator.action.processor.LogGammaCorrectionImageProcessor;
import ru.ardyc.fractalflamegenerator.action.saver.ImageFormat;
import ru.ardyc.fractalflamegenerator.model.image.FractalImage;
import ru.ardyc.fractalflamegenerator.model.world.Rect;
import ru.ardyc.fractalflamegenerator.renderer.MultiThreadRenderer;
import ru.ardyc.fractalflamegenerator.transforms.HeartTransformation;
import ru.ardyc.fractalflamegenerator.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Path("/")
public class FractalFlameController {


    @GET
    @Path("/generate")
    @Produces("image/png")
    public byte[] generateImage(
            @DefaultValue("2000") @QueryParam("width") int width,
            @DefaultValue("1000") @QueryParam("height") int height,
            @DefaultValue("-4") @QueryParam("rectX") int rectX,
            @DefaultValue("-3") @QueryParam("rectY") int rectY,
            @DefaultValue("8") @QueryParam("rectWidth") int rectWidth,
            @DefaultValue("6") @QueryParam("rectHeight") int rectHeight,
            @DefaultValue("5") @QueryParam("affineCount") int affineCount,
            @DefaultValue("5") @QueryParam("samples") int samples,
            @DefaultValue("100000") @QueryParam("iterPerSample") int iterPerSample,
            @DefaultValue("5") @QueryParam("symmetry") int symmetry
    ) throws IOException {
        FractalImage image = FractalFlameGenerator.generate(
                width,
                height,
                new Rect(rectX, rectY, rectWidth, rectHeight),
                new MultiThreadRenderer(
                        affineCount,
                        samples,
                        iterPerSample,
                        symmetry,
                        List.of(new HeartTransformation())
                ),
                List.of(new LogGammaCorrectionImageProcessor())
        );
        System.out.println(image);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(ImageUtils.convertFractalImageToBufferedImage(image), ImageFormat.PNG.getFormatName(), byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
