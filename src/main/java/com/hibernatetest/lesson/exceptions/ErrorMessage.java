package com.hibernatetest.lesson.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class ErrorMessage {

    private String errorMessage;
    private LocalDateTime errorTime;
    private Integer httpStatus;
    private String descriptionError;
}
