package ru.job4j.tictactoe.policy;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.Mark;

public interface WinPolicy {
    boolean isWin(Mark mark, Logic logic);
}
