package ru.job4j.tictactoe.table;

import static ru.job4j.tictactoe.table.Mark.Empty;

public class Cell {
    private Mark mark = Empty;

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        if (!Empty.equals(this.mark)) {
            throw new IllegalArgumentException("Cell already marked! Choose another one!");
        }
        this.mark = mark;
    }
}
