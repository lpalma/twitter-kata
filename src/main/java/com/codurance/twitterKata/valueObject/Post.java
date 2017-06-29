package com.codurance.twitterKata.valueObject;

import java.time.LocalDateTime;

public class Post {
    private User userName;
    private LocalDateTime date;
    private PostMessage message;

    public Post(User user, LocalDateTime date, PostMessage message) {
        this.userName = user;
        this.date = date;
        this.message = message;
    }

    public User user() {
        return userName;
    }

    public PostMessage message() {
        return message;
    }

    public LocalDateTime date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!userName.equals(post.userName)) return false;
        if (!date.equals(post.date)) return false;
        return message.equals(post.message);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }
}
