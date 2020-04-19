package ru.job4j.tictactoe.loop.impl;

import ru.job4j.tictactoe.loop.Loop;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;

public class Game implements Loop, StateContext {
    private State state;

    @Override
    public void loop() {
        while (!state.isEndState()) {
            state.perform(this);
        }
    }

    @Override
    public void setNext(State state) {
        this.state = state;
    }
}
