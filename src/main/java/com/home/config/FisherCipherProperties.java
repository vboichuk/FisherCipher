package com.home.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fisher-cipher")
public record FisherCipherProperties(
        int imageWidth,
        int imageHeight,
        int marginX,
        int marginY,
        int lineHeight,
        int compassSpacing,
        double letterSpacingFactor,
        double leading,
        String lettersFilePath,
        boolean isDebugDrawBox
) {}