package ru.job4j.tictactoe.player.input;

import org.junit.After;
import org.junit.Test;
import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.player.input.impl.ConsolePlayerInput;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static java.lang.System.lineSeparator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConsolePlayerInputTest {
    private final InputStream sysIn = System.in;

    @After
    public void tearDown() {
        System.setIn(sysIn);
    }

    @Test
    public void whenPlayerInputTwoNumbersThanCellShouldBeReturn() {
        System.setIn(new ByteArrayInputStream(("00").getBytes()));
        ConsolePlayerInput input = new ConsolePlayerInput(mock(MessagePrinter.class));

        assertThat(input.get(), is(new Cell(0, 0)));
    }

    @Test
    public void whenPlayerInputStringThanPrinterShouldPrintError() {
        System.setIn(new ByteArrayInputStream(("str" + lineSeparator() + "00").getBytes()));
        MessagePrinter printer = mock(MessagePrinter.class);
        ConsolePlayerInput input = new ConsolePlayerInput(printer);

        input.get();

        verify(printer).print(anyString());
    }
}