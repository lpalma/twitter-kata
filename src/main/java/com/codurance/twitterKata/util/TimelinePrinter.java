package com.codurance.twitterKata.util;

import com.codurance.twitterKata.valueObject.Post;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TimelinePrinter {

    private Console console;
    private TimelineDateFormatter timelineDateFormatter;

    public TimelinePrinter(Console console, TimelineDateFormatter timelineDateFormatter) {
        this.console = console;
        this.timelineDateFormatter = timelineDateFormatter;
    }

    public void print(List<Post> posts) {
        posts.stream()
                .sorted(Comparator.comparing(Post::date))
                .map(this::formatMessage)
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(post -> console.printLine(post));
    }

    private String formatMessage(Post post) {
        return post.user().name()
                + " - "
                + post.message()
                + " ("
                + formatTime(post.date())
                + ")";
    }

    private String formatTime(LocalDateTime date) {
        return timelineDateFormatter.format(date);
    }
}
