package com.codurance.twitterKata.repository;

import com.codurance.twitterKata.valueObject.Post;
import com.codurance.twitterKata.valueObject.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class PostRepository {
    private List<Post> posts = new ArrayList<>();

    public void add(Post post) {
        posts.add(post);
    }

    public List<Post> allPosts() {
        return unmodifiableList(posts);
    }

    public List<Post> byUser(User user) {
        return posts.stream()
                .filter(post -> post.user().equals(user))
                .collect(Collectors.toList());
    }
}
