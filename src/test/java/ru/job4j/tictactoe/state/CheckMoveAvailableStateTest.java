package ru.job4j.tictactoe.state;

import org.junit.Ignore;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore
public class CheckMoveAvailableStateTest {
//    private CellTable data;
//    private State state;
//
//    @Before
//    public void setUp() {
//        data = new GameDataTable(GameDataTable.DEFAULT_SIZE);
//        state = new CheckMoveAvailableState(
//                new StateTransitions(),
//                data,
//                new GameMessages(new ConsoleOutput()),
//                new ConsoleGameView(data, storage)
//        );
//    }
//
//    @Test
//    public void whenNotAllTableCellsAreMarkedThanAfterStatePerformTableDataShouldNotBeReset() {
//        var cell = data.getCell(0, 0);
//        cell.setMark(X);
//        state.perform();
//        assertThat(cell.getMark(), is(X));
//    }
//
//    @Test
//    public void whenAllTableCellsAreMarkedThanAfterStatePerformTableDataShouldBeReset() {
//        setMarkAllCellsWithX();
//
//        assertThat(data.getCell(0, 0).getMark(), is(X));
//
//        state.perform();
//
//        assertThat(data.getCell(0, 0).getMark(), is(Empty));
//    }
//
//    private void setMarkAllCellsWithX() {
//        for (int i = 0; i < data.getSize(); i++) {
//            for (int j = 0; j < data.getSize(); j++) {
//                data.getCell(i, j).setMark(X);
//            }
//        }
//    }
}