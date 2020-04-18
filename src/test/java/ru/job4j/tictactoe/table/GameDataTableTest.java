package ru.job4j.tictactoe.table;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;
import static ru.job4j.tictactoe.table.Mark.Empty;
import static ru.job4j.tictactoe.table.Mark.X;

public class GameDataTableTest {
    private final static int TOTAL_CELL_COUNT = DEFAULT_SIZE * DEFAULT_SIZE;
    private GameTable data;

    @Before
    public void setUp() throws Exception {
        data = new GameDataTable(DEFAULT_SIZE);
    }

    @Test
    public void whenTableCreatedThanAllCellsShouldBeEmpty() {
        var list = data.getCellsByMark(Empty);
        assertThat(list.size(), is(TOTAL_CELL_COUNT));
    }

    @Test
    public void whenResetTableThanAllCellsShouldBeEmpty() {
        data.getCell(0, 0).setMark(X);
        data.reset();
        var list = data.getCellsByMark(Empty);
        assertThat(list.size(), is(TOTAL_CELL_COUNT));
    }

    @Test
    public void whenTableCreatedThanGetSizeShouldReturnCorrectValue() {
        assertThat(data.getSize(), is(DEFAULT_SIZE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIndexIsLowerMinimumBoundThanGetCellShouldThrowException() {
        data.getCell(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIndexIsGraterMaximumBoundThanGetCellShouldThrowException() {
        data.getCell(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    @Test
    public void whenIndexesAreCorrectThanGetCellShouldReturnCell() {
        assertNotNull(data.getCell(0, 0));
    }

    @Test
    public void whenPresentOneCellWithMarkXThanGetCellsByMarkForXShouldReturnListWithOneItem() {
        data.getCell(0, 0).setMark(X);
        var list = data.getCellsByMark(X);
        assertThat(list.size(), is(1));
    }

    @Test
    public void whenPresentTwoCellsWithMarkXThanGetCellsByMarkForXShouldReturnListWithTwoItems() {
        data.getCell(0, 0).setMark(X);
        data.getCell(0, 1).setMark(X);
        var list = data.getCellsByMark(X);
        assertThat(list.size(), is(2));
    }
}