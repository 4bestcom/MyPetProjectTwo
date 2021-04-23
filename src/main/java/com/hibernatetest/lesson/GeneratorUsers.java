package com.hibernatetest.lesson;

import com.hibernatetest.lesson.enity.User;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class GeneratorUsers {

    public static User createUser() throws IOException {
        List<String> collectName = Files.lines(Path.of("d:name.txt"), StandardCharsets.UTF_8)
                .map(line -> line.split(", "))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        List<String> collectSurname = Files.lines(Path.of("d:surname.txt"))
                .collect(Collectors.toList());

        User user = new User();
        Random random = new Random();
        user.setFirstName(collectName.get(random.nextInt(collectName.size())));
        user.setSurName(collectSurname.get(random.nextInt(collectSurname.size())));
        user.setPassportNumber(createPassportNumber());
        user.setId(UUID.randomUUID());
        return user;
    }

    private static String createPassportNumber(){
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                builder.append("-");
            }
            builder.append(random.nextInt(9));
        }
        return builder.toString();
    }
}
