package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.storage.PlayerStorage;
import ru.job4j.tictactoe.table.GameTable;
import ru.job4j.tictactoe.view.ConsoleGameView;
import ru.job4j.tictactoe.view.GameView;

public class ConsoleGameStateFactory implements StateFactory {

    private State initial;

    public ConsoleGameStateFactory(PlayerStorage players, GameTable data, Messages messages, Logic logic) {
        initStates(players, data, messages, logic);
    }

    private void initStates(PlayerStorage players, GameTable data, Messages messages, Logic logic) {
        final GameView view = new ConsoleGameView(data);
        final StateTransition transitions = new StateTransitions();

        initial = new GreetingsState(transitions, messages, view, data.getSize());
        final NextPlayerState nextPlayerState = new NextPlayerState(transitions, players, messages);

        transitions.add(GreetingsState.KEY, nextPlayerState);
        transitions.add(NextPlayerState.KEY, new PlayerMoveState(transitions, messages));
        transitions.add(PlayerMoveState.KEY, new CheckExitState(transitions, messages));
        transitions.add(CheckExitState.KEY, new ShowViewAfterPlayerMoveState(transitions, view));
        transitions.add(ShowViewAfterPlayerMoveState.KEY, new CheckWinState(transitions, logic, messages));
        transitions.add(CheckWinState.KEY, new CheckAvailableMovesState(transitions, data, messages, view));
        transitions.add(CheckAvailableMovesState.KEY, nextPlayerState);
    }

    @Override
    public State initialState() {
        return initial;
    }
}
