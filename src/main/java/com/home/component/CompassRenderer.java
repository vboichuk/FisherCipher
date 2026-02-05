package com.home.component;

import com.home.model.LayoutConstraints;
import com.home.config.RenderConfig;

import java.awt.*;

public interface CompassRenderer {
    void renderIfNeeded(Graphics2D g2d, LayoutConstraints constraints, RenderConfig renderConfig);
}
