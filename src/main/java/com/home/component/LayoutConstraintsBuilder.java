package com.home.component;

import com.home.config.FisherCipherProperties;
import com.home.dto.RequestDto;
import com.home.model.LayoutConstraints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LayoutConstraintsBuilder {

    private final FisherCipherProperties properties;

    public LayoutConstraints build(RequestDto dto) {

        int letterSpacing = (int) (properties.letterSpacingFactor() * properties.lineHeight());

        return LayoutConstraints.builder()
                .imageWidth(Optional.ofNullable(dto.getWidth()).orElse(properties.imageWidth()))
                // .imageHeight(properties.imageHeight())
                .marginX(properties.marginX())
                .marginY(properties.marginY())
                .letterSpacing(letterSpacing)
                .leading(properties.leading())
                .lineHeight(Optional.ofNullable(dto.getLineHeight()).orElse(properties.lineHeight()))
                .keyDirection(dto.getKey())
                .compassWidth(2 * properties.lineHeight() + properties.compassSpacing())
                .build();
    }
}
