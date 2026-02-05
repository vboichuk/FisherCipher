package com.home.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Letter {
    private double scaleX = 0.7;
    private List<Line> lines = new ArrayList<>();
}
