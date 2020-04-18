package ru.job4j.tictactoe.game;

import ru.job4j.tictactoe.state.State;

public class GameLoop implements Loop {
    private final State initialState;

    public GameLoop(State initialState) {
        this.initialState = initialState;
    }

    @Override
    public void loop() {
        State next = initialState;
        while (next != null) {
            next = next.perform();
        }
    }
}
