package com.home.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LayoutConstraints {
    private int imageWidth;
    private int imageHeight;
    private int marginX;
    private int marginY;
    private double letterSpacing;
    private double leading;
    private int lineHeight;
    private Direction keyDirection;
    private int compassWidth;

    public int maxWidth() {
        return imageWidth - marginX; // - compassWidth;
    }

    public int getCompassX() {
        return imageWidth - marginX - compassWidth;
    }
}
