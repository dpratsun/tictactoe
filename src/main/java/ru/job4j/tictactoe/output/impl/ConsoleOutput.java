package ru.job4j.tictactoe.output.impl;

import ru.job4j.tictactoe.output.Output;

public class ConsoleOutput implements Output {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
