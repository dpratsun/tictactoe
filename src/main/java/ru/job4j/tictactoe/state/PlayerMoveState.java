package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.Player;

import static ru.job4j.tictactoe.messages.GameMessages.PLAYER_MOVE_PERFORMED_STR;

public class PlayerMoveState implements State {
    public static final String KEY = PlayerMoveState.class.getName();
    private final StateTransition transition;
    private final Messages messages;
    private Player player;

    public PlayerMoveState(StateTransition transition, Messages messages) {
        this.transition = transition;
        this.messages = messages;
    }

    @Override
    public State perform() {
        player.makeMove();
        messages.show(String.format(PLAYER_MOVE_PERFORMED_STR, player.getName()));
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
