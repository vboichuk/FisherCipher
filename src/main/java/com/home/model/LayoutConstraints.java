package com.home.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LayoutConstraints {
    private int imageWidth;
    private int marginX;
    private int marginY;
    private int letterSpacing;
    private double leading;
    private int lineHeight;
    private Direction keyDirection;
    private int compassWidth;

    public int getCompassX() {
        return imageWidth - marginX - compassWidth;
    }

    public boolean checkFit(int x, int width) {
        return x + width < imageWidth - marginX;
    }

    public int getImageHeight(int linesCount) {
        return (int) (2 * marginY
                + lineHeight * linesCount * leading
                - (leading-1) * lineHeight
        );
    }
}
