package com.home.component;

import com.home.model.BoundingBox;
import com.home.model.Letter;
import com.home.model.PositionedLetter;
import com.home.model.LayoutConstraints;
import org.springframework.stereotype.Component;

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
        List<PositionedLetter> result = new ArrayList<>();
        int currentX = constraints.getMarginX();
        int currentY = constraints.getMarginY();
        int lineHeight = constraints.getLineHeight();
        // int lineNumber = 0;

        String normalizedText = text.toUpperCase();

        for (int i = 0; i < normalizedText.length(); i++) {
            char ch = normalizedText.charAt(i);

            int charWidth;
            String character = String.valueOf(ch);
            Letter letter = letters.get(character);
            if (letter != null) {
                charWidth = (int) (letter.getScaleX() * lineHeight);
            }
            else {
                charWidth = (int) (UNDEFINED_CHAR_W * lineHeight);
            }

            // Проверяем, помещается ли символ в текущую строку
            if (currentX + charWidth > constraints.maxWidth()) {
                if (character.isBlank()) {
                    continue;
                }
                currentX = constraints.getMarginX();
                currentY += (int) (lineHeight * constraints.getLeading());
            }

            // Создаем positioned letter
            PositionedLetter positionedLetter = PositionedLetter.builder()
                    .character(character)
                    .boundingBox(new BoundingBox(
                            currentX, currentY, charWidth, lineHeight))
                    // .lineNumber(lineNumber)
                    // .positionInLine(i)
                    .build();

            result.add(positionedLetter);

            // Сдвигаем позицию для следующего символа
            currentX += (int) (charWidth + constraints.getLetterSpacing());
        }

        return result;
    }
}
