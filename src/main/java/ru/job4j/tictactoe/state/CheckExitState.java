package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.Player;

import static ru.job4j.tictactoe.messages.GameMessages.PLAYER_EXIT_STR;

public class CheckExitState implements State {
    public static final String KEY = CheckExitState.class.getName();
    public static final String EXIT_KEY = KEY + ".exit";
    private final StateTransition transition;
    private final Messages messages;
    private Player player;

    public CheckExitState(StateTransition transition, Messages messages) {
        this.transition = transition;
        this.messages = messages;
    }

    @Override
    public State perform() {
        final var state = transition.get(player.isExitGame() ? EXIT_KEY : KEY);
        if (state != null) {
            state.forPlayer(player);
        } else {
            messages.show(String.format(PLAYER_EXIT_STR, player.getName()));
        }
        return state;
    }

    @Override
    public void forPlayer(final Player player) {
        this.player = player;
    }
}
