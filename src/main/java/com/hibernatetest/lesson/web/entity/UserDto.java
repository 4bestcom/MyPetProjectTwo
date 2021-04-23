package com.hibernatetest.lesson.web.entity;

import lombok.Data;

import java.util.UUID;
@Data
public class UserDto {

    private UUID id;
    private String passportNumber;
    private String firstName;
    private String surName;
    private String patronymic;
}
