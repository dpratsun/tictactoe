package ru.job4j.tictactoe.player.service;

import ru.job4j.tictactoe.table.Cell;

import java.util.List;

public interface BotService extends PlayerService {
    List<Cell> getEmptyCells();
}
