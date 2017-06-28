package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.valueObject.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WallCommandShould {
    @Mock private CommandsService commandsService;

    @Test
    public void handleWallRequestsFromConsole() {
        User alice = new User("alice");
        WallCommand wallCommand = new WallCommand(alice, commandsService);

        wallCommand.handle();

        verify(commandsService).wall("alice");
    }
}