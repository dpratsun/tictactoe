package ru.job4j.tictactoe.player.input;

import java.util.Scanner;

public class PlayerConsoleInput implements PlayerInput {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String get() {
        return scanner.nextLine();
    }
}
