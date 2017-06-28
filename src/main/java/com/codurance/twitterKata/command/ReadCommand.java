package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.valueObject.User;

public class ReadCommand implements Command {
    private User user;
    private CommandsService commandsService;

    public ReadCommand(User user, CommandsService commandsService) {
        this.user = user;
        this.commandsService = commandsService;
    }

    public void handle() {
        commandsService.read(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadCommand that = (ReadCommand) o;

        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}
