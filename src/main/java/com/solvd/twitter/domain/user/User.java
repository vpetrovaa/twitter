package com.solvd.twitter.domain.user;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node(value = "User")
public class User {

    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    @Relationship(type = "FOLLOW", direction = Relationship.Direction.INCOMING)
    private Set<User> followers = new HashSet<>();

    @Relationship(type = "FOLLOW", direction = Relationship.Direction.OUTGOING)
    private Set<User> followings = new HashSet<>();

}
