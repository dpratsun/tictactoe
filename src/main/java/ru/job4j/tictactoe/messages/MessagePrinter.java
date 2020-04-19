package ru.job4j.tictactoe.messages;

public interface MessagePrinter {
    void print(Messages message);

    void print(Messages message, String... args);

    void print(String message);
}
