package com.home.config;

import com.home.model.Direction;
import lombok.Builder;
import lombok.Getter;

import java.awt.*;

@Getter
@Builder
public class RenderConfig {
    private Direction keyDirection;
    private boolean debugDrawBox;

    @Builder.Default
    private Color lineColor = Color.BLACK;

    @Builder.Default
    private Color dotColor = Color.BLACK;

    @Builder.Default
    private float stroke = 2.0f;

    @Builder.Default
    private int dotRadius = 5;


}
