package ru.job4j.tictactoe.logic;

import org.junit.Test;
import ru.job4j.tictactoe.policy.OneWinPolicy;
import ru.job4j.tictactoe.table.GameDataTable;
import ru.job4j.tictactoe.table.Mark;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.tictactoe.table.Mark.Empty;
import static ru.job4j.tictactoe.table.Mark.X;

public class GameLogicTest {
    @Test
    public void whenNoWinnerThanIsWinReturnFalse() {
        GameDataTable data = new GameDataTable(1);
        GameLogic logic = new GameLogic(data, new OneWinPolicy(data));
        assertFalse(logic.isWin(X));
    }

    @Test
    public void whenWinnerThanIsWinReturnTrue() {
        GameDataTable data = new GameDataTable(1);
        GameLogic logic = new GameLogic(data, new OneWinPolicy(data));
        data.getCell(0, 0).setMark(X);
        assertTrue(logic.isWin(X));
    }

    @Test
    public void whenMakeMoveMarkCorrectCell() {
        GameDataTable data = new GameDataTable(1);
        GameLogic logic = new GameLogic(data, new OneWinPolicy(data));
        assertThat(data.getCell(0, 0).getMark(), is(Empty));
        logic.makeMove(0, 0, X);
        assertThat(data.getCell(0, 0).getMark(), is(X));
    }
}