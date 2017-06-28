package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.valueObject.User;

public class PostCommand implements Command {
    private String user;
    private String text;
    private CommandsService commandsService;

    public PostCommand(String user, String text, CommandsService commandsService) {
        this.user = user;
        this.text = text;
        this.commandsService = commandsService;
    }

    public void handle() {
        commandsService.post(new User(user), text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostCommand that = (PostCommand) o;

        if (!user.equals(that.user)) return false;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
