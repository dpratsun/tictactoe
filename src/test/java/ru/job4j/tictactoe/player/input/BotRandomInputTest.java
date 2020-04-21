package ru.job4j.tictactoe.player.input;

import org.junit.Test;
import ru.job4j.tictactoe.board.BoardCells;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.Mark;
import ru.job4j.tictactoe.player.input.impl.BotRandomInput;

import java.util.List;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BotRandomInputTest {
    @Test
    public void whenGetReturnOnlyEmptyCellsInRandomOrder() {
        BoardCells cells = mock(BoardCells.class);
        var first = new Cell(0, 0);
        var second = new Cell(0, 0);
        var third = new Cell(0, 0, Mark.X);
        when(cells.get()).thenReturn(List.of(third, first, second));

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(1, 0);

        var input = new BotRandomInput(cells, random);

        assertThat(input.get(), is(second));
        assertThat(input.get(), is(first));
    }
}