package com.home.dto;

import com.home.model.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private String message;
    private Direction key = Direction.NONE;
    private Integer lineHeight;
    private Integer width;
}
