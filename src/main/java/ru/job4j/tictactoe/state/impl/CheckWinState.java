package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.messages.Messages;
import ru.job4j.tictactoe.player.provider.PlayerProvider;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;
import ru.job4j.tictactoe.state.StateStorage;

public class CheckWinState implements State {
    private final StateStorage storage;
    private final Logic logic;
    private final MessagePrinter printer;
    private final PlayerProvider provider;
    public CheckWinState(StateStorage storage, Logic logic, MessagePrinter printer, PlayerProvider provider) {
        this.storage = storage;
        this.logic = logic;
        this.printer = printer;
        this.provider = provider;
    }

    @Override
    public void perform(StateContext context) {
        var player = provider.get();
        var state = CheckMoveAvailableState.class.getName();
        if (logic.isWin(player.getMark())) {
            printer.print(Messages.PLAYER_WIN_MESSAGE, player.getName());
            state = EndState.class.getName();
        }
        context.setNext(storage.get(state));
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
