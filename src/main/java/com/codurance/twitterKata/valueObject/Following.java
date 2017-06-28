package com.codurance.twitterKata.valueObject;

public class Following {
    private String follower;
    private String following;

    public Following(String follower, String following) {
        this.follower = follower;
        this.following = following;
    }

    public Boolean hasFollower(String user) {
        return user.equals(follower);
    }

    public String following() {
        return following;
    }
}
