package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;

public class ReadCommand implements Command {
    private String user;
    private CommandsService commandsService;

    public ReadCommand(String user, CommandsService commandsService) {
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
