package com.codurance.twitterKata.util;

import com.codurance.twitterKata.command.Command;

public class CommandRunner {
    private CommandParser commandParser;

    public CommandRunner(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    public void next() {
        Command command = commandParser.parseNext();

        command.handle();
    }
}