package ru.job4j.tictactoe.player.storage;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tictactoe.player.Player;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.tictactoe.table.Mark.O;
import static ru.job4j.tictactoe.table.Mark.X;

public class PlayerListStorageTest {
    private PlayerStorage storage;

    @Before
    public void setUp() throws Exception {
        storage = new PlayerListStorage();
    }

    @Test
    public void whenAddPlayerThanGetAllShouldReturnListWithAddedPlayer() {
        Player first = mock(Player.class);
        storage.add(first);
        assertThat(storage.getAll(), is(List.of(first)));
    }

    @Test
    public void whenAddTwoPlayersWithDifferentMarksThanGetAllShouldReturnListWithAllPlayers() {
        Player first = mock(Player.class);
        when(first.getMark()).thenReturn(O);
        Player second = mock(Player.class);
        when(second.getMark()).thenReturn(X);
        storage.add(first);
        storage.add(second);
        assertThat(storage.getAll(), is(List.of(first, second)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddPlayersWithSameMarksThanExceptionShouldBeTHrown() {
        Player first = mock(Player.class);
        when(first.getMark()).thenReturn(O);
        Player second = mock(Player.class);
        when(second.getMark()).thenReturn(O);
        storage.add(first);
        storage.add(second);
    }

    @Test
    public void whenNextShouldAlwaysReturnAnotherPlayer() {
        Player first = mock(Player.class);
        when(first.getMark()).thenReturn(O);
        Player second = mock(Player.class);
        when(second.getMark()).thenReturn(X);
        storage.add(first);
        storage.add(second);
        assertThat(storage.next(null), is(first));
        assertThat(storage.next(first), is(second));
        assertThat(storage.next(second), is(first));
    }
}