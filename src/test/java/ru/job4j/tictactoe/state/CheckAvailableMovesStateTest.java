package ru.job4j.tictactoe.state;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.messages.GameMessages;
import ru.job4j.tictactoe.output.ConsoleOutput;
import ru.job4j.tictactoe.table.GameDataTable;
import ru.job4j.tictactoe.table.GameTable;
import ru.job4j.tictactoe.view.ConsoleGameView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.tictactoe.table.Mark.Empty;
import static ru.job4j.tictactoe.table.Mark.X;

public class CheckAvailableMovesStateTest {
    private GameTable data;
    private State state;

    @Before
    public void setUp() {
        data = new GameDataTable(GameDataTable.DEFAULT_SIZE);
        state = new CheckAvailableMovesState(
                new StateTransitions(),
                data,
                new GameMessages(new ConsoleOutput()),
                new ConsoleGameView(data)
        );
    }

    @Test
    public void whenNotAllTableCellsAreMarkedThanAfterStatePerformTableDataShouldNotBeReset() {
        var cell = data.getCell(0, 0);
        cell.setMark(X);
        state.perform();
        assertThat(cell.getMark(), is(X));
    }

    @Test
    public void whenAllTableCellsAreMarkedThanAfterStatePerformTableDataShouldBeReset() {
        setMarkAllCellsWithX();

        assertThat(data.getCell(0, 0).getMark(), is(X));

        state.perform();

        assertThat(data.getCell(0, 0).getMark(), is(Empty));
    }

    private void setMarkAllCellsWithX() {
        for (int i = 0; i < data.getSize(); i++) {
            for (int j = 0; j < data.getSize(); j++) {
                data.getCell(i, j).setMark(X);
            }
        }
    }
}