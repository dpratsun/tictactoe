package ru.job4j.tictactoe.messages;

import ru.job4j.tictactoe.output.Output;

public class GameMessages implements Messages {
    public final static String GAME_WELCOME_MESSAGE =
      """
      Welcome to TicTacToe Game!
      Please make moves by input pairs of numbers from 0 to %s.
      For example 12 or 00.
      """;
    public final static String PLAYER_MOVE_STR = "Player %s, please make your move...";
    public final static String PLAYER_MOVE_PERFORMED_STR = "Player %s, performed his move:";
    public final static String PLAYER_WINNER_STR = "Congratulation player %s with a win!";
    public final static String PLAYER_EXIT_STR = "Player %s want to exit game! Bye bye...";
    public final static String RESET_TABLE_STR = "Table is full. There is now winner. Reset table.";

    private final Output output;

    public GameMessages(Output output) {
        this.output = output;
    }

    @Override
    public void show(String message) {
        output.print(message);
    }
}
