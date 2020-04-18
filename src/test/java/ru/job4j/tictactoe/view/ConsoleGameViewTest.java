package ru.job4j.tictactoe.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.table.GameDataTable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;
import static ru.job4j.tictactoe.table.Mark.O;
import static ru.job4j.tictactoe.table.Mark.X;
import static ru.job4j.tictactoe.view.ConsoleGameView.*;

public class ConsoleGameViewTest {
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
    public void whenTableIsEmptyThanShouldPrintEmptyTable() {
        var view = new ConsoleGameView(new GameDataTable(DEFAULT_SIZE));
        view.show();
        var expected = EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK +
                System.lineSeparator() +
                ROW_SEPARATOR.repeat(DEFAULT_SIZE) +
                System.lineSeparator() +
                EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK +
                System.lineSeparator() +
                ROW_SEPARATOR.repeat(DEFAULT_SIZE) +
                System.lineSeparator() +
                EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK +
                System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(expected));
    }

    @Test
    public void whenTableHaveMarksThanShouldPrintWithMarks() {
        var data = new GameDataTable(DEFAULT_SIZE);
        var view = new ConsoleGameView(data);
        data.getCell(0,0).setMark(O);
        data.getCell(2,2).setMark(X);
        view.show();
        var expected = O_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK +
                System.lineSeparator() +
                ROW_SEPARATOR.repeat(DEFAULT_SIZE) +
                System.lineSeparator() +
                EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK +
                System.lineSeparator() +
                ROW_SEPARATOR.repeat(DEFAULT_SIZE) +
                System.lineSeparator() +
                EMPTY_MARK + COL_SEPARATOR + EMPTY_MARK + COL_SEPARATOR + X_MARK +
                System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(expected));
    }
}