package com.home.component;

import com.home.model.BoundingBox;
import com.home.model.Direction;
import com.home.model.LayoutConstraints;
import com.home.config.RenderConfig;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Component
public class CompassRendererImpl implements CompassRenderer {

    @Override
    public void renderIfNeeded(Graphics2D g, LayoutConstraints constraints, RenderConfig renderConfig) {

        if (renderConfig.getKeyDirection() == Direction.NONE)
            return;

        System.out.println("constraints.getCompassWidth() = " + constraints.getCompassWidth());
        System.out.println("constraints.getLineHeight() = " + constraints.getLineHeight());

        BoundingBox box = new BoundingBox(
                constraints.getCompassX(),
                constraints.getImageHeight() - constraints.getMarginY() - constraints.getLineHeight(),
                constraints.getCompassWidth(),
                constraints.getLineHeight());

        if (renderConfig.isDebugDrawBox())
            drawRect(g, box);

        int x = box.x;
        int y = box.y;

        int radius = constraints.getLineHeight() / 2;

        AffineTransform originalTransform = g.getTransform();

        g.translate(x + radius, y + radius);
        g.scale(1, -1);

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);

        for (int i = 0; i < 8; i += 2 ) {
            drawRay(g, radius, i, renderConfig);
        }

        g.translate(constraints.getCompassWidth() - 2 * radius, 0);
        g.rotate(Math.PI / 4, 0, 0);

        for (int i = 1; i < 8; i += 2 ) {
            drawRay(g, radius, i, renderConfig);
        }

        g.setTransform(originalTransform);
    }

    private void drawRay(Graphics2D g, int radius, int i, RenderConfig renderConfig) {
        if (i == renderConfig.getKeyDirection().getIndex()) {
            drawSpecialRay(g, radius);
        }
        else {
            drawCommonRay(g, radius);
        }
        g.rotate(Math.PI / 2, 0, 0);
    }

    private static void drawCommonRay(Graphics2D g, int radius) {
        g.drawLine(0, 0, radius, 0);
    }

    private static void drawSpecialRay(Graphics2D g, int radius) {
        g.drawLine(0, 0, (int) (0.6 * radius), 0);
        g.drawLine((int) (0.6 * radius), 0, (int) (0.4 * radius), (int) (-0.2 * radius));
        g.drawLine((int) (0.4 * radius), (int) (-0.2 * radius), radius, (int) (-0.2 * radius));
    }

    private void drawRect(Graphics2D g, BoundingBox box) {
        g.setColor(Color.lightGray);
        g.setStroke(new BasicStroke(1));
        g.drawRect(box.x, box.y, box.width, box.height);
    }
}
