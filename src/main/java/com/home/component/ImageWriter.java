package com.home.component;

import com.home.model.ImageData;

import java.awt.image.BufferedImage;

public interface ImageWriter {
    ImageData write(BufferedImage image, String filename);
}