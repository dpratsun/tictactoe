package ru.job4j.tictactoe.player.input;

import org.junit.Test;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.cell.Mark;
import ru.job4j.tictactoe.player.input.impl.RandomPlayerInput;

import java.util.List;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomPlayerInputTest {
    @Test
    public void whenGetReturnOnlyEmptyCellsInRandomOrder() {
        CellStorage storage = mock(CellStorage.class);
        var first = new Cell(0, 0);
        var second = new Cell(0, 0);
        var third = new Cell(0, 0, Mark.X);
        when(storage.findAll()).thenReturn(List.of(third, first, second));

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(1, 0);

        RandomPlayerInput input = new RandomPlayerInput(storage, random);

        assertThat(input.get(), is(second));
        assertThat(input.get(), is(first));
    }
}