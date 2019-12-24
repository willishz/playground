package org.willishz.playground.grammar;

import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * jdk8的日期处理
 */
public class DateTimeTest {

    @Test
    public void test() {
        LocalDateTime ldtm = LocalDateTime.of(2019, 8, 1, 0, 0, 0);
        System.out.println(ldtm);
        System.out.println(ldtm.withDayOfMonth(10));
        System.out.println(ldtm.plusDays(1));
        Duration duration = Duration.between(LocalDateTime.of(2018, 8, 1, 0, 0, 0), LocalDateTime.of(2019, 8, 1, 0, 0, 0));
        System.out.println("Duration in days: " + duration.toDays());

        final Clock clock = Clock.systemUTC(); // Get the system clock as UTC offset
        System.out.println(clock.instant());
        System.out.println(clock.millis());
    }
}