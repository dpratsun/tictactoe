package ru.job4j.tictactoe.player.input;

import org.junit.After;
import org.junit.Test;
import ru.job4j.tictactoe.message.Message;
import ru.job4j.tictactoe.player.input.impl.HumanConsoleInput;

import java.io.*;

import static java.lang.System.lineSeparator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.tictactoe.message.Message.STRING_INPUT_ERROR_MESSAGE;

public class HumanConsoleInputTest {
    private final InputStream sysIn = System.in;

    @After
    public void tearDown() {
        System.setIn(sysIn);
    }

    @Test
    public void whenPlayerInputTwoNumbersThanCellShouldBeReturn() {
        System.setIn(new ByteArrayInputStream(("00").getBytes()));
        HumanConsoleInput input = new HumanConsoleInput();

        assertThat(input.get(), is(new Pair(0, 0)));
    }

    @Test
    public void whenPlayerInputStringThanPrinterShouldPrintError() {
        System.setIn(new ByteArrayInputStream(("str" + lineSeparator() + "00").getBytes()));
        HumanConsoleInput input = new HumanConsoleInput();
        PrintStream out = System.out;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));

        input.get();

        System.setOut(out);

        assertThat(new String(os.toByteArray()), is(STRING_INPUT_ERROR_MESSAGE.getValue() + lineSeparator()));
    }
}