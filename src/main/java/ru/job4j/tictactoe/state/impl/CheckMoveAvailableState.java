package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.message.MessagePrinter;
import ru.job4j.tictactoe.player.storage.CurrentPlayerStorage;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;
import ru.job4j.tictactoe.state.StateStorage;

import static ru.job4j.tictactoe.message.Message.NO_AVAILABLE_MOVES_MESSAGE;

public class CheckMoveAvailableState implements State {
    private final StateStorage storage;
    private final MessagePrinter printer;
    private final Logic logic;
    private final CurrentPlayerStorage playerStorage;

    public CheckMoveAvailableState(StateStorage storage, MessagePrinter printer, Logic logic, CurrentPlayerStorage playerStorage) {
        this.storage = storage;
        this.printer = printer;
        this.logic = logic;
        this.playerStorage = playerStorage;
    }

    @Override
    public void perform(StateContext context) {
        playerStorage.change();
        var state = PlayerMoveState.class.getName();
        if (!logic.isMoveAvailable()) {
            printer.print(NO_AVAILABLE_MOVES_MESSAGE);
            state = InitializeState.class.getName();
        }
        context.setCurrentState(storage.get(state));
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
