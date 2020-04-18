package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.view.GameView;

public class ShowViewAfterPlayerMoveState implements State {
    public static final String KEY = ShowViewAfterPlayerMoveState.class.getName();
    private final StateTransition transition;
    private final GameView view;
    private Player player;

    public ShowViewAfterPlayerMoveState(StateTransition transition, GameView view) {
        this.transition = transition;
        this.view = view;
    }

    @Override
    public State perform() {
        view.show();
        return nextState();
    }

    @Override
    public void forPlayer(Player player) {
        this.player = player;
    }

    private State nextState() {
        final var state = transition.get(KEY);
        state.forPlayer(player);
        return state;
    }
}
