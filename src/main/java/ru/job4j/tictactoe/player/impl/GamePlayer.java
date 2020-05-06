package ru.job4j.tictactoe.player.impl;

import ru.job4j.tictactoe.player.input.Pair;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.input.PlayerInput;
import ru.job4j.tictactoe.logic.Mark;

public class GamePlayer implements Player {
    private final Mark mark;
    private final String name;
    private final Logic logic;
    private final PlayerInput input;

    public GamePlayer(Mark mark, String name, Logic logic, PlayerInput input) {
        this.mark = mark;
        this.name = name;
        this.logic = logic;
        this.input = input;
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
            try {
                Pair pair = input.get();
                logic.setMark(pair.x(), pair.y(), mark);
                moveNotPerformed = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
