package ru.job4j.tictactoe.messages.impl;

import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.output.Output;

public class GameMessagePrinter implements MessagePrinter {
    private final Output output;

    public GameMessagePrinter(Output output) {
        this.output = output;
    }

    @Override
    public void print(Messages message) {
        output.print(message.getValue());
    }

    @Override
    public void print(Messages message, String... args) {
        output.print(message.getValue(args));
    }

    @Override
    public void print(String message) {
        output.print(message);
    }
}
