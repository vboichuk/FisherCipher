package com.home.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Line {
    private Double x;
    private Double y;
    private Direction angle;

    // Конструктор для Jackson
    @JsonCreator
    public Line(@JsonProperty("x") double x,
                @JsonProperty("y") double y,
                @JsonProperty("angle") Direction angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
}
