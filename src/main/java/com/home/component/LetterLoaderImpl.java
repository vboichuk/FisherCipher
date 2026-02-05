package com.home.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.model.Letter;
import com.home.config.FisherCipherProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LetterLoaderImpl implements LetterLoader {

    private final FisherCipherProperties properties;

    @Override
    public Map<String, Letter> loadLetters() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(properties.lettersFilePath()), new TypeReference<>() { });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
