package com.codurance.twitterKata.valueObject;

public class Following {
    private User follower;
    private User following;

    public Following(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    public Boolean hasFollower(User user) {
        return user.equals(follower);
    }

    public User following() {
        return following;
    }
}
