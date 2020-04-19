package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;

public class EndState implements State {
    @Override
    public void perform(StateContext context) {

    }

    @Override
    public boolean isEndState() {
        return true;
    }
}
