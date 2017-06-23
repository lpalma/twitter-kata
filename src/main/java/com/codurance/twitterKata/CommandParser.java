package com.codurance.twitterKata;

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
        String line = console.readLine();

        String[] fragments = line.split("\\s+");

        return buildCommand(fragments);
    }

    private Command buildCommand(String... fragments) {
        String user = user(fragments);

        if (fragments.length == 1) {
            return new ReadCommand(user, commandsService);
        }

        if (command(fragments).equals(POST_COMMAND)) {
            return new PostCommand(user, text(fragments), commandsService);
        }

        if (command(fragments).equals(WALL_COMMAND)) {
            return new WallCommand(user, commandsService);
        }

        return new FollowCommand(user, text(fragments), commandsService);
    }

    private String command(String[] fragments) {
        return fragments[1];
    }

    private String text(String[] fragments) {
        String[] textFragments = copyOfRange(fragments, 2, fragments.length);

        return String.join(" ", textFragments);
    }

    private String user(String[] fragments) {
        return fragments[0];
    }
}
