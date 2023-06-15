package com.solvd.twitter.repository;

import com.solvd.twitter.domain.user.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PostRepository extends Neo4jRepository<Post, String> {

    @Query("MATCH (u:User {id: $userId}) MERGE (u)-[r:POSTED]-> (p:Post {id: :#{#post.id}, " +
            "text: :#{#post.text}, postedTime: :#{#post.postedTime}}) RETURN p")
    Post create(Post post, String userId);

    @Query("MATCH (:User {id: $userId})-[:POSTED]->(posts:Post) RETURN posts")
    List<Post> findAllPostsByUserId(String userId);

    @Query("MATCH (p:Post {id: :#{#post.id}}) SET p.text = :#{#post.text}, " +
            "u.surname = :#{#user.surname}, u.dateOfBirth = :#{#user.dateOfBirth} RETURN u")
    Post update(Post post);

    @Query(value = "MATCH (u:User {id: $userId})-[r:POSTED]->(posts:Post) " +
            "DELETE r, posts")
    void deleteAllByUserId(String userId);

}
