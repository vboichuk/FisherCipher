package com.home.component;

import com.home.model.Letter;
import com.home.model.PositionedLetter;
import com.home.config.RenderConfig;

import java.awt.*;

public interface LetterRenderer {
    void renderLetter(Graphics2D g2d, PositionedLetter pl, Letter letter, RenderConfig renderConfig);
}
