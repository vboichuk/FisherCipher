package com.home.component;

import com.home.model.*;
import com.home.config.RenderConfig;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Component
public class LetterRendererImpl implements LetterRenderer {

    public static final double H = 0.333333;  // Отношение
    public static final double D = Math.sqrt(2) * H;

    @Override
    public void renderLetter(Graphics2D g, PositionedLetter pl, Letter letter, RenderConfig renderConfig) {
        if (renderConfig.isDebugDrawBox())
            drawRect(g, pl.getBoundingBox());

        AffineTransform originalTransform = g.getTransform();

        g.translate(pl.getBoundingBox().x, pl.getBoundingBox().y + pl.getBoundingBox().height);
        g.scale(1, -1);

        letter.getLines().forEach(
                line -> drawFragment(g, line, pl.getBoundingBox().height, renderConfig));

        g.setTransform(originalTransform);
    }

    private void drawRect(Graphics2D g, Rectangle box) {
        g.setColor(Color.lightGray);
        g.setStroke(new BasicStroke(1));
        g.drawRect(box.x, box.y, box.width, box.height);
    }

    private void drawFragment(Graphics2D g, Line line, int height, RenderConfig renderConfig) {

        Point start = calculateStart(line, height);
        Point end = calculateEnd(line, height);

        if (renderConfig.getKeyDirection() == line.getAngle()) {
            drawDot(g, start, renderConfig);
        } else if (renderConfig.getKeyDirection() == line.getAngle().getOpposite()) {
            drawDot(g, end, renderConfig);
        } else {
            drawLine(g, start, end, renderConfig);
        }
    }

    private static void drawLine(Graphics2D g, Point start, Point end, RenderConfig renderConfig) {
        g.setColor(renderConfig.getLineColor());
        g.setStroke(new BasicStroke(renderConfig.getStroke()));
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    private static void drawDot(Graphics2D g, Point start, RenderConfig renderConfig) {
        int r = renderConfig.getDotRadius();
        g.setColor(renderConfig.getDotColor());
        g.fillOval(start.x - r, start.y - r, r * 2, r * 2);
    }

    private Point calculateStart(Line line, int height) {
        int x = (int) (line.getX() * height);
        int y = (int) (line.getY() * height);
        return new Point(x, y);
    }

    private Point calculateEnd(Line line, int height) {
        double rad = line.getAngle().getAngleRad();
        double len = getLengthDouble(line.getAngle());
        int x1 = (int) ((line.getX() + Math.cos(rad) * len) * height);
        int y1 = (int) ((line.getY() + Math.sin(rad) * len) * height);
        return new Point(x1, y1);
    }

    private static double getLengthDouble(Direction angle) {
        return switch (angle) {
            case E -> H;
            case NE, SE, NW, SW -> D;
            case N -> 1 - H;
            case W -> 0.0;
            case S -> 1;
            default -> throw new IllegalStateException("Unexpected value: " + angle);
        };
    }
}
