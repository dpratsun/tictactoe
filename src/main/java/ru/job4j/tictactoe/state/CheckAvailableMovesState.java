package ru.job4j.tictactoe.state;

import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.table.GameTable;
import ru.job4j.tictactoe.view.GameView;

import static ru.job4j.tictactoe.messages.GameMessages.RESET_TABLE_STR;
import static ru.job4j.tictactoe.table.Mark.Empty;

public class CheckAvailableMovesState implements State {
    public static final String KEY = CheckAvailableMovesState.class.getName();
    private final StateTransition transition;
    private final GameTable table;
    private final Messages messages;
    private final GameView view;

    public CheckAvailableMovesState(StateTransition transition, GameTable table, Messages messages, GameView view) {
        this.transition = transition;
        this.table = table;
        this.messages = messages;
        this.view = view;
    }

    @Override
    public State perform() {
        if (table.getCellsByMark(Empty).isEmpty()) {
            messages.show(RESET_TABLE_STR);
            table.reset();
            view.show();
        }
        return transition.get(KEY);
    }
}
