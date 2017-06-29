package com.codurance.twitterKata.util;

import com.codurance.twitterKata.command.*;
import com.codurance.twitterKata.valueObject.InputLine;
import com.codurance.twitterKata.valueObject.PostMessage;
import com.codurance.twitterKata.valueObject.User;

import static java.util.Arrays.copyOfRange;

public class CommandParser {
    public static final String POST_COMMAND = "->";
    public static final String WALL_COMMAND = "wall";
    private Console console;
    private CommandsService commandsService;

    public CommandParser(Console console, CommandsService commandsService) {
        this.console = console;
        this.commandsService = commandsService;
    }

    public Command parseNext() {
        InputLine line = console.readLine();

        String[] fragments = splitInput(line);

        return buildCommand(fragments);
    }

    private String[] splitInput(InputLine line) {
        return line.input()
                .split("\\s+");
    }

    private Command buildCommand(String... fragments) {
        User user = user(fragments);

        if (fragments.length == 1) {
            return new ReadCommand(user, commandsService);
        }

        if (command(fragments).equals(POST_COMMAND)) {
            PostMessage postMessage = new PostMessage(text(fragments));

            return new PostCommand(user, postMessage, commandsService);
        }

        if (command(fragments).equals(WALL_COMMAND)) {
            return new WallCommand(user, commandsService);
        }

        User following = new User(text(fragments));

        return new FollowCommand(user, following, commandsService);
    }

    private String command(String[] fragments) {
        return fragments[1];
    }

    private String text(String[] fragments) {
        String[] textFragments = copyOfRange(fragments, 2, fragments.length);

        return String.join(" ", textFragments);
    }

    private User user(String[] fragments) {
        return new User(fragments[0]);
    }
}
