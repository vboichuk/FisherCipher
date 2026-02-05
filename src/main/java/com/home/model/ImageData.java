package com.home.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class ImageData {
    private final byte[] data;
    private String name;
    private final String format;
    private int width;
    private int height;
}
