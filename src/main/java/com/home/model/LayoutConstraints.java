package com.home.model;

import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Data
@Builder
public class LayoutConstraints {
    private int imageWidth;
    private int marginX;
    private int marginY;
    private int letterSpacing;
    private double leading;
    private int lineHeight;
    private Direction keyDirection;
    private int compassWidth;

    public boolean checkFit(int x, int width) {
        return x + width < imageWidth - marginX;
    }

    public int getImageHeight(int linesCount) {
        return (int) (2 * marginY
                + lineHeight * linesCount * leading
                - (leading-1) * lineHeight
        );
    }

    public Dimension calculateFinalImageSize(List<PositionedLetter> positionedLetters) {
        int linesCount = getTextLinesCount(positionedLetters);
        if (isShouldRenderCompass(positionedLetters)) {
            linesCount++;
        }

        return new Dimension(imageWidth, getImageHeight(linesCount));
    }

    public boolean isShouldRenderCompass(List<PositionedLetter> positionedLetters) {
        return !positionedLetters.isEmpty() && keyDirection != Direction.NONE;
    }

    public Optional<Rectangle> getCompassPosition(List<PositionedLetter> positionedLetters) {
        if (!isShouldRenderCompass(positionedLetters))
            return Optional.empty();

        int textLines = getTextLinesCount(positionedLetters);

        Point position = new Point(
                imageWidth - marginX - compassWidth,
                (int) (marginY + textLines * lineHeight * (leading))
        );
        Rectangle boundingBox = new Rectangle(
                position.x,
                position.y,
                compassWidth,
                lineHeight);

        return Optional.of(boundingBox);
    }

    private int getTextLinesCount(List<PositionedLetter> positionedLetters) {
        if (positionedLetters == null || positionedLetters.isEmpty())
            return 0;

        return positionedLetters.get(positionedLetters.size()-1).getLineNumber() + 1;
    }
}
