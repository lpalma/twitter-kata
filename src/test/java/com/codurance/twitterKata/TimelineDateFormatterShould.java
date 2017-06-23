package com.codurance.twitterKata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TimelineDateFormatterShould {

    private TimelineDateFormatter formatter;
    @Mock private Clock clock;
    private LocalDateTime date;

    @Before
    public void setUp() {
        formatter = new TimelineDateFormatter(clock);
        date = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
    }

    @Test
    public void formatDateAsElapsedTimeInSeconds() {
        given(clock.now()).willReturn(date.plusSeconds(1), date.plusSeconds(2));

        assertThat(formatter.format(date), equalTo("just now"));
        assertThat(formatter.format(date), equalTo("2 seconds ago"));
    }

    @Test
    public void formatDateAsElapsedTimeInMinutes() {
        given(clock.now()).willReturn(date.plusMinutes(1), date.plusMinutes(2));

        assertThat(formatter.format(date), equalTo("1 minute ago"));
        assertThat(formatter.format(date), equalTo("2 minutes ago"));
    }

    @Test
    public void formatDateAsElapsedTimeInHours() {
        given(clock.now()).willReturn(date.plusHours(1), date.plusHours(2));

        assertThat(formatter.format(date), equalTo("1 hour ago"));
        assertThat(formatter.format(date), equalTo("2 hours ago"));
    }

    @Test
    public void formatDateAsElapsedTimeInDays() {
        given(clock.now()).willReturn(date.plusDays(1), date.plusDays(2));

        assertThat(formatter.format(date), equalTo("1 day ago"));
        assertThat(formatter.format(date), equalTo("2 days ago"));
    }
}