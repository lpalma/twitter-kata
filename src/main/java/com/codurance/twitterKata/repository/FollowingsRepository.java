package com.codurance.twitterKata.repository;

import com.codurance.twitterKata.valueObject.Following;
import com.codurance.twitterKata.valueObject.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class FollowingsRepository {
    private List<Following> followings = new ArrayList<>();

    public void add(User follower, User following) {
        followings.add(new Following(follower, following));
    }

    public List<Following> getAll() {
        return unmodifiableList(followings);
    }

    public List<String> getByFollower(User user) {
        return followings.stream()
                .filter(following -> following.hasFollower(user))
                .map(this::followingName)
                .collect(Collectors.toList());
    }

    private String followingName(Following following) {
        return following.following().name();
    }
}
