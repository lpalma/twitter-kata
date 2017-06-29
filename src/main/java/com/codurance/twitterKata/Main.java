package com.codurance.twitterKata;

import com.codurance.twitterKata.util.*;
import com.codurance.twitterKata.repository.FollowingsRepository;
import com.codurance.twitterKata.repository.PostRepository;
import com.codurance.twitterKata.valueObject.OutputLine;

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

        printWelcomeMessage();

        while(true) {
            commandRunner.next();
        }
    }

    private static void printWelcomeMessage() {
        console.printLine(new OutputLine("Welcome to Twitter Kata."));
    }
}
