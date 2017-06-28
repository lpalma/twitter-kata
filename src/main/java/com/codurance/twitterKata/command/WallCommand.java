package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.valueObject.User;

public class WallCommand implements Command {
    private User user;
    private CommandsService commandsService;

    public WallCommand(User user, CommandsService commandsService) {
        this.user = user;
        this.commandsService = commandsService;
    }

    @Override
    public void handle() {
        commandsService.wall(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallCommand that = (WallCommand) o;

        if (!user.equals(that.user)) return false;
        return commandsService.equals(that.commandsService);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + commandsService.hashCode();
        return result;
    }
}
