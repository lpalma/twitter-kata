package com.codurance.twitterKata;

import com.codurance.twitterKata.util.*;
import com.codurance.twitterKata.repository.FollowingsRepository;
import com.codurance.twitterKata.repository.PostRepository;
import com.codurance.twitterKata.valueObject.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class ViewTimelineFeature {

    @Mock private Console console;
    @Mock private Clock clock;
    private TimelineDateFormatter timelineDateFormatter;
    private CommandsService commandsService;
    private CommandParser commandParser;
    private CommandRunner commandRunner;
    private TimelinePrinter timelinePrinter;
    private PostRepository postRepository;
    private FollowingsRepository followingsRepository;

    @Before
    public void setUp() {
        postRepository = new PostRepository();
        followingsRepository = new FollowingsRepository();
        timelineDateFormatter = new TimelineDateFormatter(clock);
        timelinePrinter = new TimelinePrinter(console, timelineDateFormatter);
        commandsService = new CommandsService(postRepository, followingsRepository, clock, timelinePrinter);
        commandParser = new CommandParser(console, commandsService);
        commandRunner = new CommandRunner(commandParser);
    }

    @Test
    public void userCanPostNewMessage() {
        given(console.readLine()).willReturn(
                "alice -> hello world",
                "bob -> my first post");

        commandRunner.next();
        commandRunner.next();

        List<Post> allPosts = postRepository.allPosts();

        assertThat(allPosts.size(), equalTo(2));
    }

    @Test
    public void userTimelineCanBeVisualized() {
        given(clock.now()).willReturn(
                now().minusMinutes(5),
                now().minusMinutes(2),
                now().minusMinutes(1),
                now());

        User alice = new User("alice");

        commandsService.post(alice, "I love the weather today");
        commandsService.post(alice, "Damn! We lost!");
        commandsService.post(alice, "Good game though.");

        given(console.readLine()).willReturn("alice");

        commandRunner.next();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("alice - Good game though. (1 minute ago)");
        inOrder.verify(console).printLine("alice - Damn! We lost! (2 minutes ago)");
        inOrder.verify(console).printLine("alice - I love the weather today (5 minutes ago)");
    }

    private LocalDateTime now() {
        return LocalDate.of(2017, 1, 1).atStartOfDay();
    }

    @Test
    public void userCanFollowUsersAndVisualizeTheirTimeline() {
        given(clock.now()).willReturn(
                now().minusHours(5),
                now().minusMinutes(2),
                now().minusMinutes(1),
                now().minusSeconds(2),
                now());

        User alice = new User("alice");
        User bob = new User("bob");
        User charlie = new User("charlie");

        commandsService.post(alice, "I love the weather today");
        commandsService.post(bob, "Damn! We lost!");
        commandsService.post(alice, "Good game though.");
        commandsService.post(charlie, "Anyone want to have a coffee?");

        given(console.readLine()).willReturn(
                "charlie follows alice",
                "charlie follows bob",
                "charlie wall");

        commandRunner.next();
        commandRunner.next();
        commandRunner.next();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("charlie - Anyone want to have a coffee? (2 seconds ago)");
        inOrder.verify(console).printLine("alice - Good game though. (1 minute ago)");
        inOrder.verify(console).printLine("bob - Damn! We lost! (2 minutes ago)");
        inOrder.verify(console).printLine("alice - I love the weather today (5 hours ago)");
    }
}
