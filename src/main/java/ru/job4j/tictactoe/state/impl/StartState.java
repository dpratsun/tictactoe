package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;
import ru.job4j.tictactoe.state.StateStorage;

public class StartState implements State {
    private final StateStorage storage;
    private final MessagePrinter printer;
    private final int boardSize;

    public StartState(StateStorage storage, MessagePrinter printer, int boardSize) {
        this.storage = storage;
        this.printer = printer;
        this.boardSize = boardSize;
    }

    @Override
    public void perform(StateContext context) {
        printer.print(Messages.START_MESSAGE, String.valueOf(boardSize));
        context.setNext(storage.get(InitializeState.class.getName()));
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
