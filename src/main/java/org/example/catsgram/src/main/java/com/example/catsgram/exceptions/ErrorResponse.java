package com.example.catsgram.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private String error;
    private boolean success;
}
