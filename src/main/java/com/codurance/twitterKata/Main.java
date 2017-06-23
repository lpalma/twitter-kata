package com.codurance.twitterKata;

public class Main {

    private static Console console = new Console();

    public static void main(String[] args) {
        CommandsService commandsService = new CommandsService(
                new PostRepository(),
                new FollowingsRepository(),
                new Clock(),
                new TimelinePrinter(console, new TimelineDateFormatter(new Clock()))
        );

        CommandParser commandParser = new CommandParser(console, commandsService);
        CommandRunner commandRunner = new CommandRunner(commandParser);

        console.printLine("Welcome to Twitter Kata.");

        while(true) {
            commandRunner.next();
        }
    }
}
