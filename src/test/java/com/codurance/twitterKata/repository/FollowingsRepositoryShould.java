package com.codurance.twitterKata.repository;

import com.codurance.twitterKata.valueObject.Following;
import com.codurance.twitterKata.valueObject.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FollowingsRepositoryShould {

    private FollowingsRepository followingsRepository;
    private User alice;
    private User bob;

    @Before
    public void setUp() {
        followingsRepository = new FollowingsRepository();
        alice = new User("alice");
        bob = new User("bob");
    }

    @Test
    public void addFollowingsRelationship() {
        followingsRepository.add(alice, bob);

        List<Following> followings = followingsRepository.getAll();

        assertThat(followings.size(), equalTo(1));
    }

    @Test
    public void fetchUsersByFollower() {
        User charlie = new User("charlie");

        followingsRepository.add(alice, bob);
        followingsRepository.add(alice, charlie);

        List<String> followings = followingsRepository.getByFollower("alice");

        assertThat(followings, equalTo(asList("bob", "charlie")));
    }
}