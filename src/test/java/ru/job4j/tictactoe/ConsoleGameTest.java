package ru.job4j.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.logic.impl.TicTacToeLogic;
import ru.job4j.tictactoe.player.impl.GamePlayer;
import ru.job4j.tictactoe.player.input.Pair;
import ru.job4j.tictactoe.player.input.PlayerInput;
import ru.job4j.tictactoe.player.input.impl.BotRandomInput;
import ru.job4j.tictactoe.policy.impl.OneWinPolicy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.message.Message.PLAYER_WIN_MESSAGE;

public class ConsoleGameTest {
    private PrintStream systemOut = System.out;
    private ByteArrayOutputStream os = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(os));
    }

    @After
    public void tearDown() {
        System.setOut(systemOut);
    }

    @Test
    public void whenPlayerWinThanCongratulationMessageShouldBeDisplayed() {
        var logic = new TicTacToeLogic(new OneWinPolicy(), 2);
        var firstPlayerInput = mock(PlayerInput.class);
        when(firstPlayerInput.get()).thenReturn(new Pair(0, 0), new Pair(0, 1));
        var secondPlayerInput = mock(PlayerInput.class);
        when(secondPlayerInput.get()).thenReturn(new Pair(1, 0));

        new ConsoleGame(
                new GamePlayer(Mark.X, "Dmitry", logic, firstPlayerInput),
                new GamePlayer(Mark.O, "Bot", logic, new BotRandomInput(logic, new Random())),
                logic
        ).start();

        assertThat(
                new String(os.toByteArray()).contains(PLAYER_WIN_MESSAGE.getValue("Dmitry")),
                is(true)
        );
    }
}