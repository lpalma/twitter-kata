package com.codurance.twitterKata.command;

import com.codurance.twitterKata.command.ReadCommand;
import com.codurance.twitterKata.util.CommandsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReadCommandShould {

    @Mock private CommandsService commandsService;

    @Test
    public void handleReadRequests() {
        ReadCommand readCommand = new ReadCommand("alice", commandsService);

        readCommand.handle();

        verify(commandsService).read("alice");
    }
}