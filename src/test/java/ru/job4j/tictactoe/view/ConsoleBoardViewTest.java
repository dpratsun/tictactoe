package ru.job4j.tictactoe.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;
import ru.job4j.tictactoe.cell.Mark;
import ru.job4j.tictactoe.view.impl.ConsoleBoardView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.logic.impl.GameLogic.DEFAULT_BOARD_SIZE;
import static ru.job4j.tictactoe.view.impl.ConsoleBoardView.*;

public class ConsoleBoardViewTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream systemOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void tearDown() {
        System.setOut(systemOut);
    }

    @Test
    public void whenTableHaveMarksThanShouldPrintWithMarks() {
        CellStorage storage = mock(CellStorage.class);
        when(storage.findAll()).thenReturn(List.of(
                new Cell(0, 0, Mark.O),
                new Cell(0, 0),
                new Cell(0, 0),
                new Cell(0, 0),
                new Cell(0, 0),
                new Cell(0, 0),
                new Cell(0, 0),
                new Cell(0, 0),
                new Cell(0, 0, Mark.X)
        ));
        var view = new ConsoleBoardView(DEFAULT_BOARD_SIZE, storage);
        view.show();
        var expected = O_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK +
                System.lineSeparator() +
                ROW_SEPARATOR.repeat(DEFAULT_BOARD_SIZE) +
                System.lineSeparator() +
                EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK +
                System.lineSeparator() +
                ROW_SEPARATOR.repeat(DEFAULT_BOARD_SIZE) +
                System.lineSeparator() +
                EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + X_MARK +
                System.lineSeparator() + System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(expected));
    }
}