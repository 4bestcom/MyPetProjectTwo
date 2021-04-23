package com.hibernatetest.lesson.enity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users_group")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserGroup {

    @EqualsAndHashCode.Include
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "user_group_id")
    UUID id;
    @Column(name = "name")
    String name;
    @JoinColumn(name = "user_group_id")
    @OneToMany
    List<User> users = new ArrayList<>();
}
