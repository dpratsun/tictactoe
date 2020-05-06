package ru.job4j.tictactoe.player.input.impl;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.player.input.Pair;
import ru.job4j.tictactoe.player.input.PlayerInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.job4j.tictactoe.logic.Mark.Empty;

public class BotRandomInput implements PlayerInput {
    private final Logic logic;
    private final Random random;

    public BotRandomInput(Logic logic, Random random) {
        this.logic = logic;
        this.random = random;
    }

    @Override
    public Pair get() {
        List<Pair> emptyCells = getEmptyCells();
        return emptyCells.get(getCellIndex(emptyCells.size()));
    }

    private int getCellIndex(int listSize) {
        return listSize > 1 ? random.nextInt(listSize - 1) : 0;
    }

    private List<Pair> getEmptyCells() {
        List<Pair> result = new ArrayList<>();
        for (int x = 0; x < logic.getBoardSize(); x++) {
            for (int y = 0; y < logic.getBoardSize(); y++) {
                if (Empty.equals(logic.getMark(x, y))) {
                    result.add(new Pair(x, y));
                }
            }
        }
        return result;
    }
}