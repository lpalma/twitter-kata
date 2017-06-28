package com.codurance.twitterKata.util;

import com.codurance.twitterKata.command.FollowCommand;
import com.codurance.twitterKata.command.PostCommand;
import com.codurance.twitterKata.command.ReadCommand;
import com.codurance.twitterKata.command.WallCommand;
import com.codurance.twitterKata.util.CommandParser;
import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.util.Console;
import com.codurance.twitterKata.valueObject.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CommandParserShould {

    @Mock private Console console;
    @Mock private CommandsService commandsService;
    private CommandParser parser;
    private User alice;

    @Before
    public void setUp() {
        parser = new CommandParser(console, commandsService);
        alice = new User("alice");
    }

    @Test
    public void parsePostCommandsFromConsole() {
        given(console.readLine()).willReturn("alice -> hello world");

        PostCommand postCommand = new PostCommand(alice, "hello world", commandsService);

        assertThat(parser.parseNext(), equalTo(postCommand));
    }

    @Test
    public void parseReadCommandsFromConsole() {
        given(console.readLine()).willReturn("alice");

        ReadCommand readCommand = new ReadCommand(alice, commandsService);

        assertThat(parser.parseNext(), equalTo(readCommand));
    }

    @Test
    public void parseFollowCommandFromConsole() {
        given(console.readLine()).willReturn("alice follows bob");

        User alice = new User("alice");
        User bob = new User("bob");

        FollowCommand followCommand = new FollowCommand(alice, bob, commandsService);

        assertThat(parser.parseNext(), equalTo(followCommand));
    }

    @Test
    public void parseWallCommandFromConsole() {
        given(console.readLine()).willReturn("alice wall");

        User alice = new User("alice");

        WallCommand wallCommand = new WallCommand(alice, commandsService);

        assertThat(parser.parseNext(), equalTo(wallCommand));
    }
}