package com.solvd.twitter.service.factory;

import com.solvd.twitter.domain.user.Post;
import com.solvd.twitter.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class TwitterFactory {

    public static User generateUser() {
        return new User("1", "email@mail.ru", "password", "name",
                "surname", LocalDate.now(), Set.of(new User()), Set.of(new User()));
    }

    public static Post generatePost() {
        return new Post("1", "text", LocalDateTime.now(), generateUser());
    }

}
