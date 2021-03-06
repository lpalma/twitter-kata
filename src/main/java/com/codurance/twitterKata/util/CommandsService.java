package com.codurance.twitterKata.util;

import com.codurance.twitterKata.valueObject.Post;
import com.codurance.twitterKata.repository.FollowingsRepository;
import com.codurance.twitterKata.repository.PostRepository;
import com.codurance.twitterKata.valueObject.PostMessage;
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

    public void post(User user, PostMessage postMessage) {
        Post post = createPost(user, postMessage);

        postRepository.add(post);
    }

    public void read(User user) {
        List<Post> posts = postRepository.byUser(user);

        timelinePrinter.print(posts);
    }

    private Post createPost(User user, PostMessage postMessage) {
        return new Post(user, clock.now(), postMessage);
    }

    public void follow(User follower, User following) {
        followingsRepository.add(follower, following);
    }

    public void wall(User user) {
        List<User> followings = followingsRepository.getByFollower(user);

        ArrayList<Post> wallPosts = mergeWallPosts(user, followings);

        timelinePrinter.print(wallPosts);
    }

    private ArrayList<Post> mergeWallPosts(User user, List<User> followings) {
        ArrayList<Post> wallPosts = new ArrayList<>();

        wallPosts.addAll(postRepository.byUser(user));

        followings.stream()
                .flatMap(following -> postRepository.byUser(new User(following.name())).stream())
                .forEach(wallPosts::add);

        return wallPosts;
    }
}
