package com.example.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageReqDto {
    @Min(0)
    private int number;
    @Min(10)
    @Max(20)
    private int size;
}
