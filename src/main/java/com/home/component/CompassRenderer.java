package com.home.component;

import com.home.model.LayoutConstraints;
import com.home.config.RenderConfig;

import java.awt.*;
import java.awt.Rectangle;

public interface CompassRenderer {
    void render(Graphics2D g2d, Rectangle box, LayoutConstraints constraints, RenderConfig renderConfig);
}
