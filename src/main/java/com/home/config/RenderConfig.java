package com.home.config;

import com.home.model.Direction;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RenderConfig {
    private Direction keyDirection;
    private boolean debugDrawBox;
    @Builder.Default
    private int stroke = 3;
}
