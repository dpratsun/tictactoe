package ru.job4j.tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.player.impl.GamePlayer;
import ru.job4j.tictactoe.player.input.PlayerInput;

import static org.mockito.Mockito.*;
import static ru.job4j.tictactoe.cell.Mark.X;

public class GamePlayerTest {
    private PlayerInput input;
    private MessagePrinter printer;
    private Logic logic;
    private Player player;

    @Before
    public void setUp() {
        input = mock(PlayerInput.class);
        when(input.get()).thenReturn(mock(Cell.class));

        printer = mock(MessagePrinter.class);
        logic = mock(Logic.class);
        player = new GamePlayer(X, "Test", logic, input, printer);
    }

    @Test
    public void whenPlayerInputCorrectCellThanPrinterShouldNotPrintAnything() {
        player.makeMove();

        verify(printer, never()).print(anyString());
    }

    @Test
    public void whenPlayerInputWrongCellThanPrinterShouldPrintError() {
        doThrow(new IllegalArgumentException("Error")).doNothing().when(logic).playerMove(any(Cell.class));

        player.makeMove();

        verify(printer).print(anyString());
    }
}