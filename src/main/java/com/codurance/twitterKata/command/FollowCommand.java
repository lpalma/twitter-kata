package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.valueObject.User;

public class FollowCommand implements Command {
    private User follower;
    private User following;
    private CommandsService commandsService;

    public FollowCommand(User follower, User following, CommandsService commandsService) {
        this.follower = follower;
        this.following = following;
        this.commandsService = commandsService;
    }

    public void handle() {
        commandsService.follow(follower, following);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowCommand that = (FollowCommand) o;

        if (!follower.equals(that.follower)) return false;
        return following.equals(that.following);
    }

    @Override
    public int hashCode() {
        int result = follower.hashCode();
        result = 31 * result + following.hashCode();
        return result;
    }
}
