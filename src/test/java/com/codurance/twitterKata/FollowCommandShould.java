package com.codurance.twitterKata;

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
        FollowCommand followCommand = new FollowCommand("alice", "bob", commandsService);

        followCommand.handle();

        verify(commandsService).follow("alice", "bob");
    }
}