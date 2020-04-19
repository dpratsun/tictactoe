package ru.job4j.tictactoe.messages;

public enum Messages {
    START_MESSAGE(
      """
      Welcome to TicTacToe Game!
      Please make moves by input pairs of numbers from 0 to %s.
      For example 12 or 00.
      """),
    PLAYER_MAKE_MOVE_MESSAGE("Player %s, please make your move..."),
    PLAYER_MOVE_PERFORMED_MESSAGE("Player %s, performed his move:"),
    PLAYER_WIN_MESSAGE("Congratulation player %s with a win!"),
    NO_AVAILABLE_MOVES_MESSAGE("There are no more available moves. Resetting the table."),
    INDEX_INPUT_ERROR("Seems you input wrong indexes for cell. Please input two numbers from 0 to %s.");

    private final String value;

    Messages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getValue(String... args) {
        return String.format(value, args);
    }
}
