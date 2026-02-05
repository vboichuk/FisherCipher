package com.home.component;

import com.home.model.Letter;
import com.home.model.PositionedLetter;
import com.home.model.LayoutConstraints;

import java.util.List;
import java.util.Map;

public interface TextLayoutEngine {
    List<PositionedLetter> layout(String text, LayoutConstraints constraints, Map<String, Letter> letters);
}