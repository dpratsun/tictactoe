package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.table.Mark;
import ru.job4j.tictactoe.player.service.HumanService;

import java.util.function.BiFunction;

public class Human implements Player {
    private final Mark mark;
    private final String name;
    private final HumanService service;
    private boolean exitGame = false;

    private BiFunction<String, Integer, Integer> intParser =
            (s, i) -> Integer.parseInt(String.valueOf(s.charAt(i)));

    public Human(Mark mark, String name, HumanService service) {
        this.mark = mark;
        this.name = name;
        this.service = service;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void makeMove() {
        var movePerformed = false;
        while (!movePerformed) {
            var input = service.getInput();
            movePerformed = isExit(input) || isMovePerformed(input);
        }
    }

    @Override
    public boolean isExitGame() {
        return exitGame;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    private boolean isMovePerformed(String input) {
        var result = false;
        try {
            service.makeMove(intParser.apply(input, 0), intParser.apply(input, 1), mark);
            result = true;
        } catch (IllegalArgumentException e) {
            service.print(e.getMessage());
        }
        return result;
    }

    private boolean isExit(String input) {
        var result = false;
        if ("exit".equals(input.toLowerCase())) {
            exitGame = true;
            result = true;
        }
        return result;
    }
}
