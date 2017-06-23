package com.codurance.twitterKata;

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