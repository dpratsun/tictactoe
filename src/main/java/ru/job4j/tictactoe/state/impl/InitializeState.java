package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.view.View;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;
import ru.job4j.tictactoe.state.StateStorage;

public class InitializeState implements State {
    private final StateStorage factory;
    private final Logic logic;
    private final View view;

    public InitializeState(StateStorage factory, Logic logic, View view) {
        this.factory = factory;
        this.logic = logic;
        this.view = view;
    }

    @Override
    public void perform(StateContext context) {
        logic.reset();
        view.show();
        context.setNext(factory.get(PlayerMoveState.class.getName()));
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
