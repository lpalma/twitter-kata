package com.codurance.twitterKata.util;

import com.codurance.twitterKata.command.FollowCommand;
import com.codurance.twitterKata.command.PostCommand;
import com.codurance.twitterKata.command.ReadCommand;
import com.codurance.twitterKata.command.WallCommand;
import com.codurance.twitterKata.util.CommandParser;
import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.util.Console;
import com.codurance.twitterKata.valueObject.InputLine;
import com.codurance.twitterKata.valueObject.PostMessage;
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
        InputLine inputLine = new InputLine("alice -> hello world");

        given(console.readLine()).willReturn(inputLine);

        PostMessage aliceMessage = new PostMessage("hello world");

        PostCommand postCommand = new PostCommand(alice, aliceMessage, commandsService);

        assertThat(parser.parseNext(), equalTo(postCommand));
    }

    @Test
    public void parseReadCommandsFromConsole() {
        InputLine input = new InputLine("alice");

        given(console.readLine()).willReturn(input);

        ReadCommand readCommand = new ReadCommand(alice, commandsService);

        assertThat(parser.parseNext(), equalTo(readCommand));
    }

    @Test
    public void parseFollowCommandFromConsole() {
        InputLine input = new InputLine("alice follows bob");

        given(console.readLine()).willReturn(input);

        User alice = new User("alice");
        User bob = new User("bob");

        FollowCommand followCommand = new FollowCommand(alice, bob, commandsService);

        assertThat(parser.parseNext(), equalTo(followCommand));
    }

    @Test
    public void parseWallCommandFromConsole() {
        InputLine input = new InputLine("alice wall");

        given(console.readLine()).willReturn(input);

        User alice = new User("alice");

        WallCommand wallCommand = new WallCommand(alice, commandsService);

        assertThat(parser.parseNext(), equalTo(wallCommand));
    }
}