package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.view.GameView;

import static ru.job4j.tictactoe.messages.GameMessages.GAME_WELCOME_MESSAGE;

public class GreetingsState implements State {
    public static final String KEY = GreetingsState.class.getName();
    private final StateTransition transition;
    private final Messages messages;
    private final GameView view;
    private final int tableSize;

    public GreetingsState(StateTransition transition, Messages messages, GameView view, int tableSize) {
        this.transition = transition;
        this.messages = messages;
        this.view = view;
        this.tableSize = tableSize;
    }

    @Override
    public State perform() {
        messages.show(String.format(GAME_WELCOME_MESSAGE, tableSize));
        view.show();
        return transition.get(KEY);
    }
}
