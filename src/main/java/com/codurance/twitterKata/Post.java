package com.codurance.twitterKata;

import java.time.LocalDateTime;

public class Post {
    private String userName;
    private LocalDateTime date;
    private String message;

    public Post(String userName, LocalDateTime date, String message) {
        this.userName = userName;
        this.date = date;
        this.message = message;
    }

    public String user() {
        return userName;
    }

    public String message() {
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
