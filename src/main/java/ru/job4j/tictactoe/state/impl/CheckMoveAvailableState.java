package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;
import ru.job4j.tictactoe.state.StateStorage;

import static ru.job4j.tictactoe.messages.Messages.NO_AVAILABLE_MOVES_MESSAGE;

public class CheckMoveAvailableState implements State {
    private final StateStorage storage;
    private final MessagePrinter printer;
    private final Logic logic;

    public CheckMoveAvailableState(StateStorage storage, MessagePrinter printer, Logic logic) {
        this.storage = storage;
        this.printer = printer;
        this.logic = logic;
    }

    @Override
    public void perform(StateContext context) {
        var state = PlayerMoveState.class.getName();
        if (!logic.isMoveAvailable()) {
            printer.print(NO_AVAILABLE_MOVES_MESSAGE);
            state = InitializeState.class.getName();
        }
        context.setNext(storage.get(state));
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
