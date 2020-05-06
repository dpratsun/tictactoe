package ru.job4j.tictactoe.player.input;

import org.junit.Test;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.player.input.impl.BotRandomInput;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.logic.Mark.Empty;
import static ru.job4j.tictactoe.logic.Mark.X;

public class BotRandomInputTest {
    @Test
    public void whenGetReturnOnlyEmptyCellsInRandomOrder() {
        Logic logic = mock(Logic.class);
        when(logic.getMark(0, 0)).thenReturn(X);
        when(logic.getMark(0, 1)).thenReturn(Empty);
        when(logic.getMark(1, 0)).thenReturn(Empty);
        when(logic.getMark(1, 1)).thenReturn(X);
        when(logic.getBoardSize()).thenReturn(2);

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(1, 0);

        var input = new BotRandomInput(logic, random);

        assertThat(input.get(), is(new Pair(1, 0)));
        assertThat(input.get(), is(new Pair(0, 1)));
    }
}