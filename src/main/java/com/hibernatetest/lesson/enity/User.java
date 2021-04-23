package com.hibernatetest.lesson.enity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ApiModel
public class User {
    @Id
    @Column(name = "user_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surName;
    @Column(name = "patronymic")
    private String patronymic;
}
