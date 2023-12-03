package ru.ardyc.fractalflamegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenerationRequest {

    private int width = 2000;
    private int height = 1000;
    private int rectX = -4;
    private int rectY = -3;
    private int rectWidth = 8;
    private int rectHeight = 6;
    private int affineCount = 5;
    private int samples = 5;
    private int iterPerSample = 100000;
    private int symmetry = 5;

    public static void create(
            int width,
            int height,
            int rectX,
            int rectY,
            int rectWidth,
            int rectHeight,
            int affineCount,
            int samples,
            int iterPerSample,
            int symmetry
    ) {
        GenerationRequest request = new GenerationRequest();
        if (isValid(width)) {
            request.setWidth(width);
        }
        if (isValid(height)) {
            request.setHeight(height);
        }
        if (isValid(rectX)) {
            request.setRectX(rectX);
        }
        if (isValid(rectY)) {
            request.setRectY(rectY);
        }
        if (isValid(rectWidth)) {
            request.setRectWidth(rectWidth);
        }
        if (isValid(rectHeight)) {
            request.setRectHeight(rectHeight);
        }
        if (isValid(affineCount)) {
            request.setAffineCount(affineCount);
        }
        if (isValid(samples)) {
            request.setSamples(samples);
        }
        if (isValid(iterPerSample)) {
            request.setIterPerSample(iterPerSample);
        }
        if (isValid(symmetry)) {
            request.setSymmetry(symmetry);
        }
    }

    private static boolean isValid(int value) {
        return value > 0;
    }

}
