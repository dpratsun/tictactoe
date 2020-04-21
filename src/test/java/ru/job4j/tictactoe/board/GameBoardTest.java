package ru.job4j.tictactoe.board;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.board.impl.GameBoard;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.cell.CellStorage;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameBoardTest {
    private final static int BOARD_TEST_SIZE = 2;
    private CellStorage storage;
    private Board board;

    @Before
    public void setUp() {
        storage = mock(CellStorage.class);
        board = new GameBoard(storage, BOARD_TEST_SIZE);
    }

    @Test
    public void whenGetSizeShouldReturnActualSize() {
        assertThat(board.size(), is(BOARD_TEST_SIZE));
    }

    @Test
    public void whenGetThanShouldReturnListOfCells() {
        Cell cell = new Cell(0, 0);
        when(storage.findAll()).thenReturn(List.of(cell));

        assertThat(board.get(), is(List.of(cell)));
    }

    @Test
    public void whenGetCellByXYThanShouldReturnCell() {
        Cell cell = new Cell(0, 0);
        when(storage.find(any(Cell.class))).thenReturn(cell);

        assertThat(board.get(0, 0), is(cell));
    }

    @Test
    public void whenUpdateCellThanStorageUpdateShouldBeInvoked() {
        Cell cell = new Cell(0, 0);

        board.update(cell);

        verify(storage).update(cell);
    }

    @Test
    public void whenResetThanStorageClearShouldBeInvokedAndProperAmountOfCellsShouldBeAdded() {
        board.reset();

        verify(storage).clear();
        verify(storage, times(BOARD_TEST_SIZE * BOARD_TEST_SIZE)).add(any(Cell.class));
    }
}