package ru.job4j.tictactoe.output;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.output.impl.ConsoleOutput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.lineSeparator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleOutputTest {
    private PrintStream out = System.out;
    private ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(byteArray));
    }

    @After
    public void tearDown() {
        System.setOut(out);
    }

    @Test
    public void name() {
        new ConsoleOutput().print("Test");
        assertThat(new String(byteArray.toByteArray()), is("Test" + lineSeparator()));
    }
}