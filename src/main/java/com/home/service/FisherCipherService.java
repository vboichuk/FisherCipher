package com.home.service;

import com.home.component.*;
import com.home.config.RenderConfig;
import com.home.dto.RequestDto;
import com.home.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FisherCipherService {

    private final LayoutConstraintsBuilder layoutConstraintsBuilder;
    private final LetterLoader letterLoader;
    private final TextLayoutEngine layoutEngine;
    private final LetterRenderer letterRenderer;
    private final CompassRenderer compassRenderer;
    private final ImageWriter imageWriter;

    public ImageData generate(RequestDto requestDto) {
        LayoutConstraints constraints = layoutConstraintsBuilder.build(requestDto);
        Map<String, Letter> letterMap = letterLoader.loadLetters();
        List<PositionedLetter> positionedLetters = layoutEngine.layout(requestDto.getMessage(), constraints, letterMap);

        RenderConfig renderConfig = createRenderConfig(requestDto.getKey());

        Dimension imageSize = constraints.calculateFinalImageSize(positionedLetters);
        BufferedImage image = createImage(imageSize);
        Graphics2D g2d = image.createGraphics();

        try {
            renderBackground(g2d, image);
            positionedLetters.forEach(pl ->
                    renderLetter(g2d, pl, letterMap.get(pl.getCharacter()), renderConfig));

            Optional<BoundingBox> box = constraints.getCompassPosition(positionedLetters);
            box.ifPresent(b -> compassRenderer.render(g2d, b, constraints, renderConfig));
        } finally {
            g2d.dispose();
        }

        return imageWriter.write(image, "test.png");
    }

    private RenderConfig createRenderConfig(Direction direction) {
        return RenderConfig.builder()
                .keyDirection(direction)
                .debugDrawBox(true)
                .build();
    }

    private void renderLetter(Graphics2D g2d, PositionedLetter pl, Letter letter, RenderConfig renderConfig) {
        String character = pl.getCharacter();
        if (letter != null) {
            letterRenderer.renderLetter(g2d, pl, letter, renderConfig);
        } else if (!character.trim().isEmpty()) {
            renderUnknownCharacter(g2d, pl);
        }
    }

    private void renderUnknownCharacter(Graphics2D g2d,
                                        PositionedLetter positionedLetter) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(
                positionedLetter.getBoundingBox().x,
                positionedLetter.getBoundingBox().y,
                positionedLetter.getBoundingBox().width,
                positionedLetter.getBoundingBox().height
        );
    }

    private BufferedImage createImage(Dimension dimension) {
        return new BufferedImage(
                dimension.width,
                dimension.height,
                BufferedImage.TYPE_INT_RGB);
    }

    private void renderBackground(Graphics2D g2d, BufferedImage image) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(1, 1, image.getWidth()-2, image.getHeight()-2);
    }
}
