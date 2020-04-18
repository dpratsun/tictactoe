package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.storage.PlayerStorage;

import static ru.job4j.tictactoe.messages.GameMessages.PLAYER_MOVE_STR;

public class NextPlayerState implements State {
    public static final String KEY = NextPlayerState.class.getName();
    private final StateTransition transition;
    private final PlayerStorage players;
    private final Messages messages;
    private Player player = null;

    public NextPlayerState(StateTransition transition, PlayerStorage players, Messages messages) {
        this.transition = transition;
        this.players = players;
        this.messages = messages;
    }

    @Override
    public State perform() {
        player = players.next(player);
        messages.show(String.format(PLAYER_MOVE_STR, player.getName()));
        return nextState();
    }

    private State nextState() {
        final var state = transition.get(KEY);
        state.forPlayer(player);
        return state;
    }
}
