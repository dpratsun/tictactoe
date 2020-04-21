package ru.job4j.tictactoe.player.input.impl;

import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.player.input.PlayerInput;

import java.util.Scanner;

public class HumanConsoleInput implements PlayerInput {
    private final Scanner scanner = new Scanner(System.in);
    private final MessagePrinter printer;

    public HumanConsoleInput(MessagePrinter printer) {
        this.printer = printer;
    }

    @Override
    public Cell get() {
        Cell result = null;
        while (result == null) {
            try {
                var input = scanner.nextLine();
                result = new Cell(parse(input, 0), parse(input, 1));
            } catch (Exception e) {
                printer.print(e.getMessage());
            }
        }
        return result;
    }

    private int parse(String input, int index) {
        return Integer.parseInt(String.valueOf(input.charAt(index)));
    }
}
