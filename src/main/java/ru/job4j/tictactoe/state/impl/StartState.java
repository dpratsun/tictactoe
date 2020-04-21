package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.board.BoardSize;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.message.Message;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;
import ru.job4j.tictactoe.state.StateStorage;

import java.util.Objects;

public class StartState implements State {
    private final StateStorage storage;
    private final MessagePrinter printer;
    private final BoardSize boardSize;

    public StartState(StateStorage storage, MessagePrinter printer, BoardSize boardSize) {
        this.storage = storage;
        this.printer = printer;
        this.boardSize = boardSize;
    }

    @Override
    public void perform(StateContext context) {
        printer.print(Message.START_MESSAGE, String.valueOf(boardSize.size()));
        context.setCurrentState(storage.get(InitializeState.class.getName()));
    }

    @Override
    public boolean isEndState() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StartState that = (StartState) o;
        return Objects.equals(storage, that.storage)
                && Objects.equals(printer, that.printer)
                && Objects.equals(boardSize, that.boardSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage, printer, boardSize);
    }
}
