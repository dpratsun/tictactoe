package ru.job4j.tictactoe.player;

import org.junit.Test;
import ru.job4j.tictactoe.player.service.BotServiceImpl;
import ru.job4j.tictactoe.table.GameDataTable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;
import static ru.job4j.tictactoe.table.Mark.*;

public class BotTest {
    @Test
    public void whenBotFillAllTableThanNoExceptionShouldBeThrown() {
        GameDataTable data = new GameDataTable(DEFAULT_SIZE);
        Bot bot = new Bot(X, "Test", new BotServiceImpl(data));
        while (!data.getCellsByMark(Empty).isEmpty()) {
            bot.makeMove();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoCellsToMoveThanExceptionShouldBeThrown() {
        GameDataTable data = new GameDataTable(1);
        Bot bot = new Bot(X, "Test", new BotServiceImpl(data));
        data.getCell(0, 0).setMark(O);
        bot.makeMove();
    }

    @Test
    public void whenOneCellFrom4MarkedThanBy3BotMovesTableShouldBeFilled() {
        GameDataTable data = new GameDataTable(2);
        Bot bot = new Bot(X, "Test", new BotServiceImpl(data));

        data.getCell(0, 0).setMark(O);
        assertFalse(data.getCellsByMark(Empty).isEmpty());

        bot.makeMove();
        bot.makeMove();
        bot.makeMove();
        assertTrue(data.getCellsByMark(Empty).isEmpty());
    }
}