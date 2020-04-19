package ru.job4j.tictactoe.cell;

import java.util.Objects;

import static ru.job4j.tictactoe.cell.Mark.Empty;

public class Cell {
    private final int x;
    private final int y;
    private Mark mark = Empty;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        var result = false;
        if (o instanceof Cell c && x == c.x && y == c.y) {
            result = true;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
