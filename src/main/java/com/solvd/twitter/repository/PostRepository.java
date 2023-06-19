package com.solvd.twitter.repository;

import com.solvd.twitter.domain.user.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends Neo4jRepository<Post, String> {

    @Query(value = "MATCH (u:User), (p:Post) WHERE u.id=$userId AND p.id=$postId " +
            "MERGE (u)-[r:POSTED]->(p) return p")
    Post createRelationship(@Param("postId") String postId, @Param("userId") String userId);

    @Query("MATCH (:User {id: $userId})-[:POSTED]->(posts:Post) RETURN posts")
    List<Post> findAllPostsByUserId(String userId);

    @Query("MATCH (p:Post {id: :#{#post.id}}) SET p.text = :#{#post.text} RETURN p")
    Post update(Post post);

    @Query(value = "MATCH (u:User {id: $userId})-[r:POSTED]->(posts:Post) " +
            "DELETE r, posts")
    void deleteAllByUserId(String userId);

}
