package com.codurance.twitterKata.util;

import com.codurance.twitterKata.valueObject.Post;
import com.codurance.twitterKata.valueObject.PostMessage;
import com.codurance.twitterKata.valueObject.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

@RunWith(MockitoJUnitRunner.class)
public class TimelinePrinterShould {

    @Mock private Console console;
    @Mock private TimelineDateFormatter timelineDateFormatter;
    private TimelinePrinter timelinePrinter;

    @Before
    public void setUp() {
        timelinePrinter = new TimelinePrinter(console, timelineDateFormatter);
    }

    @Test
    public void displayPostsInReverseChronologicalOrder() {
        Post firstPost = post("alice", LocalDateTime.of(2017, 1, 1, 0, 0, 0), "first post");
        Post secondPost = post("alice", LocalDateTime.of(2017, 1, 2, 10, 56, 0), "second post");
        Post thirdPost = post("alice", LocalDateTime.of(2017, 1, 2, 11, 0, 0), "third post");

        List<Post> posts = asList(firstPost, secondPost, thirdPost);

        given(timelineDateFormatter.format(firstPost.date())).willReturn("1 day ago");
        given(timelineDateFormatter.format(secondPost.date())).willReturn("5 minutes ago");
        given(timelineDateFormatter.format(thirdPost.date())).willReturn("1 minute ago");

        timelinePrinter.print(posts);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("alice - third post (1 minute ago)");
        inOrder.verify(console).printLine("alice - second post (5 minutes ago)");
        inOrder.verify(console).printLine("alice - first post (1 day ago)");
    }

    private Post post(String user, LocalDateTime date, String text) {
        return new Post(new User(user), date, new PostMessage(text));
    }
}