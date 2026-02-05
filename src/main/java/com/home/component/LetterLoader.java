package com.home.component;

import com.home.model.Letter;

import java.util.Map;

public interface LetterLoader {
    Map<String, Letter> loadLetters();
}
