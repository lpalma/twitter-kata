package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.valueObject.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FollowCommandShould {
    @Mock private CommandsService commandsService;

    @Test
    public void handleFollowRequest() {
        User alice = new User("alice");
        User bob = new User("bob");

        FollowCommand followCommand = new FollowCommand(alice, bob, commandsService);

        followCommand.handle();

        verify(commandsService).follow(alice, bob);
    }
}