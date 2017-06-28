package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;

public class FollowCommand implements Command {
    private String follower;
    private String following;
    private CommandsService commandsService;

    public FollowCommand(String follower, String following, CommandsService commandsService) {
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
