package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.valueObject.PostMessage;
import com.codurance.twitterKata.valueObject.User;

public class PostCommand implements Command {
    private User user;
    private PostMessage postMessage;
    private CommandsService commandsService;

    public PostCommand(User user, PostMessage postMessage, CommandsService commandsService) {
        this.user = user;
        this.postMessage = postMessage;
        this.commandsService = commandsService;
    }

    public void handle() {
        commandsService.post(user, postMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostCommand that = (PostCommand) o;

        if (!user.equals(that.user)) return false;
        return postMessage.equals(that.postMessage);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + postMessage.hashCode();
        return result;
    }
}
