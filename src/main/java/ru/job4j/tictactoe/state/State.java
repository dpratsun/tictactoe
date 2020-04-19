package ru.job4j.tictactoe.state;

public interface State {
    void perform(StateContext context);

    boolean isEndState();
}
