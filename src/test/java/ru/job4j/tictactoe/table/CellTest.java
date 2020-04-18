package ru.job4j.tictactoe.table;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.tictactoe.table.Mark.*;

public class CellTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenTryToMarkAlreadyMarkedCellThanExceptionShouldBeThrown() {
        Cell cell = new Cell();
        cell.setMark(X);
        cell.setMark(O);
    }

    @Test
    public void whenNewCellCreatedThanGetMarkShouldRetrunEmptyValue() {
        Cell cell = new Cell();
        assertThat(cell.getMark(), is(Empty));
    }

    @Test
    public void whenSetCellMarkThanGetMarkShouldReturnNotEmptyValue() {
        Cell cell = new Cell();
        cell.setMark(X);
        assertThat(cell.getMark(), is(X));
    }
}