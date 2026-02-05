package com.home.model;

public enum Direction {
    E,
    NE,
    N,
    NW,
    W,
    SW,
    S,
    SE,
    NONE;

    public int getIndex() {
        return switch (this) {
            case E -> 0;
            case NE -> 1;
            case N -> 2;
            case NW -> 3;
            case W -> 4;
            case SW -> 5;
            case S -> 6;
            case SE -> 7;
            case NONE -> -1;
        };
    }

    public Direction getOpposite() {
        return switch (this) {
            case E -> W;
            case NE -> SW;
            case N -> S;
            case NW -> SE;
            case W -> E;
            case SW -> NE;
            case S -> N;
            case SE -> NW;
            case NONE -> NONE;
        };
    }

    public double getAngleRad() {
        return switch (this) {
            case E -> 0.0;
            case NE -> Math.PI / 4;
            case N -> Math.PI / 2;
            case NW -> 3 * Math.PI / 4;
            case W -> Math.PI;
            case SW -> 5.0 * Math.PI / 4.0;
            case S -> -Math.PI / 2;
            case SE -> -Math.PI / 4;
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }
}
