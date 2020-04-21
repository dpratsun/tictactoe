package ru.job4j.tictactoe.message.impl;

import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.message.Message;
import ru.job4j.tictactoe.output.Output;

public class GameMessagePrinter implements MessagePrinter {
    private final Output output;

    public GameMessagePrinter(Output output) {
        this.output = output;
    }

    @Override
    public void print(Message message) {
        output.print(message.getValue());
    }

    @Override
    public void print(Message message, String... args) {
        output.print(message.getValue(args));
    }

    @Override
    public void print(String message) {
        output.print(message);
    }
}
