package ru.job4j.tictactoe;

import ru.job4j.tictactoe.game.Game;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.logic.impl.TicTacToeLogic;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.impl.GamePlayer;
import ru.job4j.tictactoe.player.input.impl.BotRandomInput;
import ru.job4j.tictactoe.player.input.impl.HumanConsoleInput;
import ru.job4j.tictactoe.policy.impl.MultipleWinsPolicy;
import ru.job4j.tictactoe.policy.impl.OneWinPolicy;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

import static java.lang.System.lineSeparator;
import static ru.job4j.tictactoe.logic.impl.TicTacToeLogic.DEFAULT_BOARD_SIZE;
import static ru.job4j.tictactoe.message.Message.*;
import static ru.job4j.tictactoe.policy.impl.MultipleWinsPolicy.DEFAULT_COUNT_TO_WIN;

public class ConsoleGame implements Game {
    private final Map<Player, Player> nextPlayer = new HashMap<>(2);
    private Player currentPlayer;
    private final Logic logic;

    public ConsoleGame(Player firstPlayer, Player secondPlayer, Logic logic) {
        nextPlayer.put(firstPlayer, secondPlayer);
        nextPlayer.put(secondPlayer, firstPlayer);
        this.currentPlayer = firstPlayer;
        this.logic = logic;
    }

    @Override
    public void start() {
        var playGame = true;
        initializeGame();
        while (playGame) {
            playerMove();
            playGame = !isWinner();
            if (playGame) {
                resetGameBoardIfNoMovesLeft();
                changeCurrentPlayer();
            }
        }
    }

    @Override
    public void displayBoard() {
        Predicate<Integer> isLast = i -> i == logic.getBoardSize() - 1;
        for (int x = 0; x < logic.getBoardSize(); x++) {
            for (int y = 0; y < logic.getBoardSize(); y++) {
                printCell(logic.getMark(x, y));
                System.out.print(isLast.test(y) ? lineSeparator() : "|");
            }
            System.out.println(isLast.test(x) ? "" : "----".repeat(logic.getBoardSize()));
        }
    }

    private void printCell(Mark mark) {
        switch (mark) {
            case O -> System.out.print(" O ");
            case X -> System.out.print(" X ");
            case Empty -> System.out.print("   ");
        }
    }

    private void initializeGame() {
        printMessage(START_MESSAGE.getValue());
        displayBoard();
    }

    private void playerMove() {
        printMessage(PLAYER_MAKE_MOVE_MESSAGE.getValue(currentPlayer.getName()));
        currentPlayer.makeMove();
        printMessage(PLAYER_MOVE_PERFORMED_MESSAGE.getValue(currentPlayer.getName()));
        displayBoard();
    }

    private boolean isWinner() {
        var result = false;
        if (logic.isWin(currentPlayer.getMark())) {
            printMessage(PLAYER_WIN_MESSAGE.getValue(currentPlayer.getName()));
            result = true;
        }
        return result;
    }

    private void resetGameBoardIfNoMovesLeft() {
        if (!logic.isEmptyMarkPresent()) {
            printMessage(NO_AVAILABLE_MOVES_MESSAGE.getValue());
            logic.resetGameBoard(DEFAULT_BOARD_SIZE);
        }
    }

    private void changeCurrentPlayer() {
        currentPlayer = nextPlayer.get(currentPlayer);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        var logic = new TicTacToeLogic(
                new MultipleWinsPolicy(new OneWinPolicy(), DEFAULT_COUNT_TO_WIN),
                DEFAULT_BOARD_SIZE
        );
        new ConsoleGame(
                new GamePlayer(Mark.X, "Dmitry", logic, new HumanConsoleInput()),
                new GamePlayer(Mark.O, "Bot", logic, new BotRandomInput(logic, new Random())),
                logic
        ).start();
    }
}