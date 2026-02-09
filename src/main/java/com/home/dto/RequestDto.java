package com.home.dto;

import com.home.model.Direction;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    @NotBlank
    private String message;
    private Direction key = Direction.NONE;

    @Min(10)
    private Integer lineHeight;

    @Min(100)
    private Integer width;
}
