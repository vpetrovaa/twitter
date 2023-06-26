package com.solvd.twitter.service;

import com.solvd.twitter.domain.exception.ResourceDoesNotExistException;
import com.solvd.twitter.domain.user.Post;
import com.solvd.twitter.repository.PostRepository;
import com.solvd.twitter.service.impl.PostServiceImpl;
import com.solvd.twitter.service.factory.TwitterFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void setUp(){
        postService = new PostServiceImpl(postRepository);
    }

    @Test
    void verifyCreateTest() {
        Post post = TwitterFactory.generatePost();
        String userId = "1";
        String postId = "1";
        post.setId(null);
        Mockito.doAnswer(invocation -> {
            Post postCreated = invocation.getArgument(0);
            postCreated.setId(postId);
            return post;
        }).when(postRepository).save(post);
        Mockito.when(postRepository.createRelationship(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(post);
        Post postCreated = postService.create(post, userId);
        Assertions.assertEquals(post, postCreated,
                "Assert post and postCreated are equals");
        Mockito.verify(postRepository, Mockito.times(1))
                .save(post);
        Mockito.verify(postRepository, Mockito.times(1))
                .createRelationship(post.getId(), userId);
    }

    @Test
    void verifyFindByIdPassedTest() {
        Post post = TwitterFactory.generatePost();
        String postId = "1";
        Mockito.when(postRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(post));
        Post postFounded = postService.findById(postId);
        Assertions.assertEquals(post, postFounded,
                "Assert that post and postFounded are equals");
        Mockito.verify(postRepository, Mockito.times(1))
                .findById(postId);
    }

    @Test
    void verifyFindByIdFailedTest() {
        String postId = "1";
        Mockito.when(postRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceDoesNotExistException.class,
                () -> postService.findById(postId),
                "Assert ResourceDoesNotExistException");
        Mockito.verify(postRepository, Mockito.times(1))
                .findById(postId);
    }

    @Test
    void verifyFindAllPostsByUserIdTest() {
        String userId = "1";
        Post post = TwitterFactory.generatePost();
        List<Post> posts = new ArrayList<>(List.of(post));
        Mockito.when(postRepository.findAllPostsByUserId(Mockito.anyString()))
                .thenReturn(posts);
        List<Post> postsFounded = postService.findAllPostsByUserId(userId);
        Assertions.assertEquals(posts.size(), postsFounded.size(),
                "Assert number of posts is the same");
        Mockito.verify(postRepository, Mockito.times(1))
                .findAllPostsByUserId(userId);
    }

    @Test
    void verifyUpdateTest() {
        Post post = TwitterFactory.generatePost();
        String postId = "1";
        Mockito.when(postRepository.update(Mockito.any())).thenReturn(post);
        Post postUpdated = postService.update(post);
        Assertions.assertEquals(postId, postUpdated.getId(),
                "Assert that postId and postUpdated id are equals");
        Mockito.verify(postRepository, Mockito.times(1))
                .update(post);
    }

    @Test
    void verifyDeleteByIdTest() {
        String postId = "1";
        postService.deleteById(postId);
        Mockito.verify(postRepository, Mockito.times(1))
                .deleteById(postId);
    }

    @Test
    void verifyDeleteAllByUserIdTest() {
        String postId = "1";
        postService.deleteAllByUserId(postId);
        Mockito.verify(postRepository, Mockito.times(1))
                .deleteAllByUserId(postId);
    }

}
