package com.codurance.twitterKata;

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

    public void post(String user, String text) {
        Post post = createPost(user, text);

        postRepository.add(post);
    }

    public void read(String user) {
        List<Post> posts = postRepository.byUser(user);

        timelinePrinter.print(posts);
    }

    private Post createPost(String user, String text) {
        return new Post(user, clock.now(), text);
    }

    public void follow(String follower, String following) {
        followingsRepository.add(follower, following);
    }

    public void wall(String user) {
        List<String> followings = followingsRepository.getByFollower(user);

        ArrayList<Post> wallPosts = mergeWallPosts(user, followings);

        timelinePrinter.print(wallPosts);
    }

    private ArrayList<Post> mergeWallPosts(String user, List<String> followings) {
        ArrayList<Post> wallPosts = new ArrayList<>();

        wallPosts.addAll(postRepository.byUser(user));

        followings.stream()
                .flatMap(following -> postRepository.byUser(following).stream())
                .forEach(wallPosts::add);

        return wallPosts;
    }
}
