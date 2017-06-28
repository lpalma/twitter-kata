package com.codurance.twitterKata.util;

import com.codurance.twitterKata.valueObject.Post;
import com.codurance.twitterKata.repository.FollowingsRepository;
import com.codurance.twitterKata.repository.PostRepository;
import com.codurance.twitterKata.valueObject.User;

import java.util.ArrayList;
import java.util.List;

public class CommandsService {
    private PostRepository postRepository;
    private FollowingsRepository followingsRepository;
    private Clock clock;
    private TimelinePrinter timelinePrinter;

    public CommandsService(PostRepository postRepository, FollowingsRepository followingsRepository, Clock clock, TimelinePrinter timelinePrinter) {
        this.postRepository = postRepository;
        this.followingsRepository = followingsRepository;
        this.clock = clock;
        this.timelinePrinter = timelinePrinter;
    }

    public void post(User user, String text) {
        Post post = createPost(user, text);

        postRepository.add(post);
    }

    public void read(User user) {
        List<Post> posts = postRepository.byUser(user.name());

        timelinePrinter.print(posts);
    }

    private Post createPost(User user, String text) {
        return new Post(user, clock.now(), text);
    }

    public void follow(User follower, User following) {
        followingsRepository.add(follower.name(), following.name());
    }

    public void wall(User user) {
        List<String> followings = followingsRepository.getByFollower(user.name());

        ArrayList<Post> wallPosts = mergeWallPosts(user, followings);

        timelinePrinter.print(wallPosts);
    }

    private ArrayList<Post> mergeWallPosts(User user, List<String> followings) {
        ArrayList<Post> wallPosts = new ArrayList<>();

        wallPosts.addAll(postRepository.byUser(user.name()));

        followings.stream()
                .flatMap(following -> postRepository.byUser(following).stream())
                .forEach(wallPosts::add);

        return wallPosts;
    }
}
