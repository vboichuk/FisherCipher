package com.home.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionedLetter {
    String character;
    BoundingBox boundingBox;
    int lineNumber;
}
