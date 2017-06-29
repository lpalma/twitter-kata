package com.codurance.twitterKata.repository;

import com.codurance.twitterKata.valueObject.Post;
import com.codurance.twitterKata.valueObject.PostMessage;
import com.codurance.twitterKata.valueObject.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostRepositoryShould {

    private PostRepository postRepository;
    private User alice;

    @Before
    public void setUp() {
        postRepository = new PostRepository();
        alice = new User("alice");
    }

    @Test
    public void storePosts() {
        Post post = dummyPost();

        postRepository.add(post);

        List<Post> posts = postRepository.allPosts();

        assertThat(posts.size(), equalTo(1));
    }

    @Test
    public void retrievePostsByUser() {
        Post post = dummyPost();

        postRepository.add(post);

        assertThat(postRepository.byUser(alice), equalTo(singletonList(post)));
    }

    private Post dummyPost() {
        return new Post(alice, LocalDateTime.of(2017, 1, 1, 0, 0, 0), new PostMessage("my post"));
    }
}