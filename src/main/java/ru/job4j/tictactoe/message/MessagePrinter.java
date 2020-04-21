package ru.job4j.tictactoe.message;

public interface MessagePrinter {
    void print(Message message);

    void print(Message message, String... args);

    void print(String message);
}
