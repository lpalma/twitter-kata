package com.codurance.twitterKata.util;

import com.codurance.twitterKata.valueObject.Post;
import com.codurance.twitterKata.repository.FollowingsRepository;
import com.codurance.twitterKata.repository.PostRepository;
import com.codurance.twitterKata.valueObject.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandsServiceShould {

    @Mock private PostRepository postRepository;
    @Mock private Clock clock;
    @Mock private TimelinePrinter timelinePrinter;
    @Mock private FollowingsRepository followingsRepository;
    private CommandsService commandsService;
    private User alice;
    private User bob;
    private User charlie;

    @Before
    public void setUp() {
        commandsService = new CommandsService(postRepository, followingsRepository, clock, timelinePrinter);
        alice = new User("alice");
        bob = new User("bob");
    }

    @Test
    public void acceptPostsFromUser() {
        LocalDateTime date = LocalDateTime.of(2017, 6, 1, 0,0,0);
        Post post = post("alice", date, "hello world");

        given(clock.now()).willReturn(date);

        commandsService.post(alice, "hello world");

        verify(postRepository).add(post);
    }

    @Test
    public void displayPostsFromUser() {
        LocalDateTime date = LocalDateTime.of(2017, 6, 1, 0,0,0);
        List<Post> posts = singletonList(post("alice", date, "hello world"));

        given(postRepository.byUser(alice)).willReturn(posts);

        commandsService.read(alice);

        verify(timelinePrinter).print(posts);
    }

    @Test
    public void letUserFollowOtherUser() {
        commandsService.follow(alice, bob);

        verify(followingsRepository).add(alice, bob);
    }

    @Test
    public void displayUserWall() {
        LocalDateTime date = LocalDateTime.of(2017, 6, 1, 0,0,0);
        charlie = new User("charlie");

        Post alicePost = post("alice", date, "hello world");
        Post charliePost = post("charlie", date, "I'm being followed!");
        Post bobPost = post("bob", date, "I like Terminator 2");

        given(followingsRepository.getByFollower(alice)).willReturn(asList(charlie, bob));

        given(postRepository.byUser(alice)).willReturn(singletonList(alicePost));
        given(postRepository.byUser(charlie)).willReturn(singletonList(charliePost));
        given(postRepository.byUser(bob)).willReturn(singletonList(bobPost));

        commandsService.wall(alice);

        verify(timelinePrinter).print(asList(alicePost, charliePost, bobPost));
    }

    private Post post(String user, LocalDateTime date, String text) {
        return new Post(new User(user), date, text);
    }
}