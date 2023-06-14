package com.solvd.twitter.repository;

import com.solvd.twitter.domain.user.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, String> {

    boolean existsByEmail(String email);

    @Query("MATCH (:User {id: $id})<-[:FOLLOW]-(followers:User) RETURN followers")
    List<User> findFollowersByUserId(String id);

    @Query("MATCH (:User {id: $id})-[:FOLLOW]->(followings:User) RETURN followings")
    List<User> findFollowingsByUserId(String id);

    @Query(value = "MATCH (u:User), (f:User) WHERE u.id=$id  AND f.id=$followerId " +
            "CREATE (u)<-[r:FOLLOW]-(f) return f")
    User createFollowerById(@Param("id") String id, @Param("followerId") String followerId);

    @Query(value = "MATCH (u:User), (f:User) WHERE u.id=$id  AND f.id=$followingId " +
            "CREATE (u)-[r:FOLLOW]->(f) return f")
    User createFollowingById(@Param("id") String id, @Param("followingId") String followingId);

    @Query("MATCH (u:User {id: :#{#user.id}}) SET u.name = :#{#user.name}, " +
            "u.surname = :#{#user.surname}, u.dateOfBirth = :#{#user.dateOfBirth} RETURN u")
    User update(@Param("user") User user);

}
