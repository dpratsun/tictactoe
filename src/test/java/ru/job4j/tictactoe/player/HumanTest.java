package ru.job4j.tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.logic.GameLogic;
import ru.job4j.tictactoe.player.service.HumanServiceImpl;
import ru.job4j.tictactoe.policy.OneWinPolicy;
import ru.job4j.tictactoe.table.GameDataTable;
import ru.job4j.tictactoe.table.GameTable;

import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.tictactoe.table.GameDataTable.DEFAULT_SIZE;
import static ru.job4j.tictactoe.table.Mark.O;
import static ru.job4j.tictactoe.table.Mark.X;

public class HumanTest {
    private Scanner playerInput;
    private GameTable data;
    private Player player;
    private String message;
    private Scanner scanner;

    @Before
    public void setUp() throws Exception {
        data = new GameDataTable(DEFAULT_SIZE);
        player = new Human(X, "Test", new HumanServiceImpl(
                () -> playerInput.nextLine(),
                new GameLogic(data, new OneWinPolicy(data)),
                message -> this.message = message
            )
        );
    }

    @Test
    public void whenPlayerInputCoordinatesOfEmptyCellThanMessageShouldBeNull() {
        playerInput = new Scanner("01");
        player.makeMove();
        assertNull(message);
    }

    @Test
    public void whenPlayerInputExitThanIsExitGameShouldReturnTrue() {
        playerInput = new Scanner("exit");
        player.makeMove();
        assertTrue(player.isExitGame());
    }

    @Test
    public void whenPlayerInputCoordinatesOfMarkedCellThanMessageShouldNotBeNull() {
        playerInput = new Scanner("00" + System.lineSeparator() + "01");
        data.getCell(0, 0).setMark(O);
        player.makeMove();
        assertThat(message, is("Cell already marked! Choose another one!"));
    }

    @Test
    public void whenPlayerInputNotCorrectIntValuesThanMessageShouldNotBeNull() {
        playerInput = new Scanner("33" + System.lineSeparator() + "00");
        player.makeMove();
        assertThat(message, is("Index should be lower than 3!"));
    }
}