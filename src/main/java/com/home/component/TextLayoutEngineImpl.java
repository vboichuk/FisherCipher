package com.home.component;

import com.home.model.Letter;
import com.home.model.PositionedLetter;
import com.home.model.LayoutConstraints;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TextLayoutEngineImpl implements TextLayoutEngine {

    public static final double UNDEFINED_CHAR_W = 0.6;

    @Override
    public List<PositionedLetter> layout(String text,
                                         LayoutConstraints constraints,
                                         Map<String, Letter> letters) {
        if (text == null || text.isBlank()) {
            return List.of();
        }

        List<PositionedLetter> result = new ArrayList<>();

        String normalizedText = text.toUpperCase();

        LayoutContext layoutContext = new LayoutContext(constraints);

        for (int i = 0; i < normalizedText.length(); i++) {
            char character = normalizedText.charAt(i);
            processCharacter(character, letters, constraints, result, layoutContext);
        }

        return result;
    }

    private void processCharacter(char character, Map<String, Letter> letters, LayoutConstraints constraints, List<PositionedLetter> result, LayoutContext layoutContext) {
        String charStr = String.valueOf(character);

        if (charStr.equals("\n")) {
            processNewlineCharacter(layoutContext);
        } else if (charStr.equals(" ")) {
            processSpaceCharacter(layoutContext, constraints);
        } else {
            processRegularCharacter(charStr, layoutContext, letters, constraints, result);
        }
    }

    private void processSpaceCharacter(LayoutContext context, LayoutConstraints constraints) {
        int charWidth = getCharWidth(" ", null, constraints.getLineHeight());
        if (constraints.checkFit(context.x, charWidth)) {
            context.moveCursor(charWidth);
        } else {
            context.nextLine();
        }
    }

    private void processRegularCharacter(String charStr, LayoutContext context, Map<String, Letter> letterMap, LayoutConstraints constraints, List<PositionedLetter> result) {
        int charWidth = getCharWidth(charStr, letterMap.get(charStr), constraints.getLineHeight());

        if (!constraints.checkFit(context.x, charWidth)) {
            context.nextLine();
        }

        PositionedLetter positionedLetter = PositionedLetter.builder()
                .character(charStr)
                .boundingBox(new Rectangle(context.x, context.y, charWidth, constraints.getLineHeight()))
                .lineNumber(context.lineNum)
                .build();

        result.add(positionedLetter);
        context.moveCursor(charWidth);
    }

    private void processNewlineCharacter(LayoutContext context) {
        context.nextLine();
    }

    private static int getCharWidth(String s, Letter letter, int lineHeight) {
        if (s.equals("\n"))
            return 0;

        if (letter != null)
            return (int) (letter.getScaleX() * lineHeight);

        return (int) (UNDEFINED_CHAR_W * lineHeight);
    }

    private static class LayoutContext {
        private final LayoutConstraints constraints;
        int x;
        int y;
        int lineNum;

        LayoutContext (LayoutConstraints constraints) {
            this.constraints = constraints;
            x = constraints.getMarginX();
            y = constraints.getMarginY();
            lineNum = 0;
        }

        public void nextLine() {
            x = constraints.getMarginX();
            y += (int) (constraints.getLineHeight() * constraints.getLeading());
            lineNum++;
        }

        public void moveCursor(int charWidth) {
            x += charWidth + constraints.getLetterSpacing();
        }
    }
}
