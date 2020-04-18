package ru.job4j.tictactoe.output;

public class ConsoleOutput implements Output {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
