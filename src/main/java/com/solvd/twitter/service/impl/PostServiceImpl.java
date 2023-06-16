package com.solvd.twitter.service.impl;

import com.solvd.twitter.domain.exception.ResourceDoesNotExistException;
import com.solvd.twitter.domain.user.Post;
import com.solvd.twitter.repository.PostRepository;
import com.solvd.twitter.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post create(Post post, String userId) {
        post.setPostedTime(LocalDateTime.now());
        post = postRepository.save(post);
        return postRepository.createRelationship(post.getId(), userId);
    }

    @Override
    public Post findById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException("There" +
                        " are no post with id " + id));
    }

    @Override
    public List<Post> findAllPostsByUserId(String userId) {
        return postRepository.findAllPostsByUserId(userId);
    }

    @Override
    public Post update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public void deleteById(String id) {
        postRepository.deleteById(id);
    }

    @Override
    public void deleteAllByUserId(String userId) {
        postRepository.deleteAllByUserId(userId);
    }
}
