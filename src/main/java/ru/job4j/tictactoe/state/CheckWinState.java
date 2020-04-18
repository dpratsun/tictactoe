package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.Player;

import static ru.job4j.tictactoe.messages.GameMessages.PLAYER_WINNER_STR;

public class CheckWinState implements State {
    public static final String KEY = CheckWinState.class.getName();
    private static final String WIN_KEY = KEY + ".win";
    private final StateTransition transition;
    private final Logic logic;
    private final Messages messages;

    private Player player;

    public CheckWinState(StateTransition transition, Logic logic, Messages messages) {
        this.transition = transition;
        this.logic = logic;
        this.messages = messages;
    }

    @Override
    public State perform() {
        State result = transition.get(KEY);
        if (logic.isWin(player.getMark())) {
            messages.show(String.format(PLAYER_WINNER_STR, player.getName()));
            result = transition.get(WIN_KEY);
        }
        return result;
    }

    @Override
    public void forPlayer(final Player player) {
        this.player = player;
    }
}
