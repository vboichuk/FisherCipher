package com.home.model;

import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
@Builder
public class PositionedLetter {
    String character;
    Rectangle boundingBox;
    int lineNumber;
}
