package com.solvd.twitter.repository;

import com.solvd.twitter.domain.user.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, String> {

    boolean existsByEmail(String email);

    @Query("MATCH  (u:User {id: $id}), (f:User {id: $followerId}) " +
            "RETURN EXISTS((u)<-[:FOLLOW]-(f))")
    boolean isAFollower(@Param("id") String id, @Param("followerId") String followerId);

    @Query("MATCH  (u:User {id: $id}), (f:User {id: $followingId}) " +
            "RETURN EXISTS((u)-[:FOLLOW]->(f))")
    boolean isAFollowing(@Param("id") String id, @Param("followingId") String followingId);

    @Query(value = "MATCH (u:User), (f:User) WHERE u.id=$id AND f.id=$followingId " +
            "MERGE (u)-[r:FOLLOW]->(f) return f")
    User createFollowingById(@Param("id") String id, @Param("followingId") String followingId);

    @Query("MATCH (:User {id: $id})<-[:FOLLOW]-(followers:User) RETURN followers")
    List<User> findFollowersByUserId(String id);

    @Query("MATCH (:User {id: $id})-[:FOLLOW]->(followings:User) RETURN followings")
    List<User> findFollowingsByUserId(String id);

    @Query("MATCH (u:User {id: :#{#user.id}}) SET u.name = :#{#user.name}, " +
            "u.surname = :#{#user.surname}, u.dateOfBirth = :#{#user.dateOfBirth} RETURN u")
    User update(@Param("user") User user);

    @Query(value = "MATCH (u:User {id: $id})-[r:FOLLOW]->(f:User {id: $followingId}) " +
            "DELETE r")
    User deleteFollowingById(@Param("id") String id, @Param("followingId") String followingId);

    @Query(value = "MATCH (u:User {id: $id})<-[r:FOLLOW]-(f:User {id: $followerId}) " +
            "DELETE r")
    User deleteFollowerById(@Param("id") String id, @Param("followerId") String followerId);

}
