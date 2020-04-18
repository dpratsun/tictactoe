package ru.job4j.tictactoe.game;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameLoopTest {

    @Test
    public void whenLoopShouldPerformTwoStatesAndThanExit() {
        var sb = new StringBuilder();
        new GameLoop(() -> {
            sb.append("state one ");
            return () -> {
                sb.append("state two");
                return null;
            };
        }).loop();
        assertThat(sb.toString(), is("state one state two"));
    }
}