package ru.job4j.tictactoe.state;

public interface StateContext {
    void setCurrentState(State state);

    State getCurrentState();
}
