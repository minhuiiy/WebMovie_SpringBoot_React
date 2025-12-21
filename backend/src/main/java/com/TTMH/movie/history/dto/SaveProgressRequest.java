package com.TTMH.movie.history.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class SaveProgressRequest {
    @Min(0)
    private int progressSeconds;
}
