package ru.job4j.tictactoe.player.service;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.input.PlayerInput;
import ru.job4j.tictactoe.table.Mark;

public class HumanServiceImpl implements HumanService {
    private final PlayerInput input;
    private final Logic logic;
    private final Messages messages;

    public HumanServiceImpl(PlayerInput input, Logic logic, Messages messages) {
        this.input = input;
        this.logic = logic;
        this.messages = messages;
    }

    @Override
    public String getInput() {
        return input.get();
    }

    @Override
    public void makeMove(int x, int y, Mark mark) {
        logic.makeMove(x, y, mark);
    }

    @Override
    public void print(String message) {
       messages.show(message);
    }
}
