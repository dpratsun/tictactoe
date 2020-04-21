package ru.job4j.tictactoe.game.impl;

import ru.job4j.tictactoe.game.Game;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;

public class TicTacToeGame implements Game, StateContext {
    private State state;

    @Override
    public void start() {
        while (!state.isEndState()) {
            state.perform(this);
        }
    }

    @Override
    public void setCurrentState(State state) {
        this.state = state;
    }

    @Override
    public State getCurrentState() {
        return state;
    }
}
