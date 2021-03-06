package com.codurance.twitterKata.command;

import com.codurance.twitterKata.util.CommandsService;
import com.codurance.twitterKata.valueObject.PostMessage;
import com.codurance.twitterKata.valueObject.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostCommandShould {
    @Mock private CommandsService commandsService;

    @Test
    public void handlePostRequests() {
        User alice = new User("alice");
        PostMessage aliceMessage = new PostMessage("hello world");
        PostCommand postCommand = new PostCommand(alice, aliceMessage, commandsService);

        postCommand.handle();

        verify(commandsService).post(alice, aliceMessage);
    }
}