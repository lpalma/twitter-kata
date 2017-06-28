package com.codurance.twitterKata.repository;

import com.codurance.twitterKata.valueObject.Following;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class FollowingsRepository {
    private List<Following> followings = new ArrayList<>();

    public void add(String follower, String following) {
        followings.add(new Following(follower, following));
    }

    public List<Following> getAll() {
        return unmodifiableList(followings);
    }

    public List<String> getByFollower(String user) {
        return followings.stream()
                .filter(following -> following.hasFollower(user))
                .map(following -> following.following())
                .collect(Collectors.toList());
    }
}
