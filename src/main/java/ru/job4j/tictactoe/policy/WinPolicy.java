package ru.job4j.tictactoe.policy;

import ru.job4j.tictactoe.cell.Mark;

public interface WinPolicy {
    boolean isWin(Mark mark);
}
