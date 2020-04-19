package ru.job4j.tictactoe.state.impl;

import ru.job4j.tictactoe.view.View;
import ru.job4j.tictactoe.messages.MessagePrinter;
import ru.job4j.tictactoe.player.provider.PlayerProvider;
import ru.job4j.tictactoe.state.State;
import ru.job4j.tictactoe.state.StateContext;
import ru.job4j.tictactoe.state.StateStorage;

import static ru.job4j.tictactoe.messages.Messages.PLAYER_MAKE_MOVE_MESSAGE;
import static ru.job4j.tictactoe.messages.Messages.PLAYER_MOVE_PERFORMED_MESSAGE;

public class PlayerMoveState implements State {
    private final StateStorage storage;
    private final PlayerProvider provider;
    private final MessagePrinter printer;
    private final View view;

    public PlayerMoveState(StateStorage storage, PlayerProvider provider, MessagePrinter printer, View view) {
        this.storage = storage;
        this.provider = provider;
        this.printer = printer;
        this.view = view;
    }

    @Override
    public void perform(StateContext context) {
        provider.change();
        var player = provider.get();
        printer.print(PLAYER_MAKE_MOVE_MESSAGE, player.getName());
        player.makeMove();
        printer.print(PLAYER_MOVE_PERFORMED_MESSAGE, player.getName());
        view.show();
        context.setNext(storage.get(CheckWinState.class.getName()));
    }

    @Override
    public boolean isEndState() {
        return false;
    }
}
