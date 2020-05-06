package ru.job4j.tictactoe.player.input.impl;

import ru.job4j.tictactoe.player.input.Pair;
import ru.job4j.tictactoe.player.input.PlayerInput;

import java.util.Scanner;

import static ru.job4j.tictactoe.message.Message.STRING_INPUT_ERROR_MESSAGE;

public class HumanConsoleInput implements PlayerInput {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Pair get() {
        Pair result = null;
        while (result == null) {
            try {
                var input = scanner.nextLine();
                result = new Pair(parse(input, 0), parse(input, 1));
            } catch (Exception e) {
                System.out.println(STRING_INPUT_ERROR_MESSAGE.getValue());
            }
        }
        return result;
    }

    private int parse(String input, int index) {
        return Integer.parseInt(String.valueOf(input.charAt(index)));
    }
}
