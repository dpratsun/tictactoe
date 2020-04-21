package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.player.storage.CurrentPlayerStorage;
import ru.job4j.tictactoe.view.View;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;
import ru.job4j.tictactoe.state.StateStorage;

public class InitializeState implements State {
    private final StateStorage storage;
    private final Logic logic;
    private final View view;
    private final CurrentPlayerStorage playerStorage;

    public InitializeState(StateStorage storage, Logic logic, View view, CurrentPlayerStorage playerStorage) {
        this.storage = storage;
        this.logic = logic;
        this.view = view;
        this.playerStorage = playerStorage;
    }

    @Override
    public void perform(StateContext context) {
        playerStorage.change();
        logic.resetBoard();
        view.show();
        context.setCurrentState(storage.get(PlayerMoveState.class.getName()));
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
