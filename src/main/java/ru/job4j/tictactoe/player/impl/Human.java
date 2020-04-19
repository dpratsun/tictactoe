package ru.job4j.tictactoe.player.impl;

import ru.job4j.tictactoe.cell.Cell;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.input.PlayerInput;
import ru.job4j.tictactoe.cell.Mark;

public class Human implements Player {
    private final Mark mark;
    private final String name;
    private final Logic logic;
    private final PlayerInput input;
    private final MessagePrinter printer;

    public Human(Mark mark, String name, Logic logic, PlayerInput input, MessagePrinter printer) {
        this.mark = mark;
        this.name = name;
        this.logic = logic;
        this.input = input;
        this.printer = printer;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void makeMove() {
        var moveNotPerformed = true;
        while (moveNotPerformed) {
            Cell cell = input.get();
            cell.setMark(mark);
            try {
                logic.makeMove(cell);
                moveNotPerformed = false;
            } catch (IllegalArgumentException e) {
                printer.print(e.getMessage());
            }
        }
    }
}
