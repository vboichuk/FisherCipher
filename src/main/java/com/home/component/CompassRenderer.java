package com.home.component;

import com.home.model.BoundingBox;
import com.home.model.LayoutConstraints;
import com.home.config.RenderConfig;

import java.awt.*;

public interface CompassRenderer {
    void render(Graphics2D g2d, BoundingBox box, LayoutConstraints constraints, RenderConfig renderConfig);
}
