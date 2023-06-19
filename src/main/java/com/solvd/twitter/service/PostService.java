package com.solvd.twitter.service;

import com.solvd.twitter.domain.user.Post;

import java.util.List;

public interface PostService {

    Post create(Post post, String userId);

    Post findById(String id);

    List<Post> findAllPostsByUserId(String userId);

    Post update(Post post);

    void deleteById(String id);

    void deleteAllByUserId(String userId);

}
