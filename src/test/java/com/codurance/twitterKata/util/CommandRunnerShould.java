package com.codurance.twitterKata.util;

import com.codurance.twitterKata.command.Command;
import com.codurance.twitterKata.util.CommandParser;
import com.codurance.twitterKata.util.CommandRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandRunnerShould {

    @Mock private CommandParser commandParser;
    @Mock private Command command;
    private CommandRunner commandRunner;

    @Before
    public void setUp() {
        commandRunner = new CommandRunner(commandParser);
    }

    @Test
    public void executeNextCommand() {
        given(commandParser.parseNext()).willReturn(command);

        commandRunner.next();

        verify(command).handle();
    }
}