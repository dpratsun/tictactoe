package ru.job4j.tictactoe.cell;

import java.util.Objects;

import static ru.job4j.tictactoe.cell.Mark.Empty;
import static ru.job4j.tictactoe.message.Message.CELL_MARKED_ERROR_MESSAGE;

public class Cell {
    private final int x;
    private final int y;
    private Mark mark = Empty;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, Mark mark) {
        this.x = x;
        this.y = y;
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        if (!Empty.equals(this.mark)) {
            throw new IllegalArgumentException(CELL_MARKED_ERROR_MESSAGE.getValue());
        }
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
