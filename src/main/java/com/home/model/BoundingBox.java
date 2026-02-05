package com.home.model;

import lombok.ToString;

@ToString
public class BoundingBox {

    public int x;
    public int y;
    public int width;
    public int height;

    public BoundingBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getRight() {
        return x + width;
    }
}
