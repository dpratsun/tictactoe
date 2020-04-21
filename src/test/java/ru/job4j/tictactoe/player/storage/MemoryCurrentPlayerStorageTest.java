package ru.job4j.tictactoe.player.storage;

import org.junit.Test;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.provider.CurrentPlayerProvider;
import ru.job4j.tictactoe.player.storage.impl.MemoryCurrentPlayerStorage;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MemoryCurrentPlayerStorageTest {
    @Test
    public void whenGetThanPlayerShouldBeReturned() {
        PlayerStorage storage = mock(PlayerStorage.class);
        Player first = mock(Player.class);
        when(storage.getAll()).thenReturn(List.of(first));
        MemoryCurrentPlayerStorage currentPlayerStorage = new MemoryCurrentPlayerStorage(storage);
        currentPlayerStorage.change();

        assertThat(currentPlayerStorage.get(), is(first));
    }

    @Test
    public void whenChangeThanGetShouldReturnAnotherPlayer() {
        PlayerStorage storage = mock(PlayerStorage.class);
        Player first = mock(Player.class);
        Player second = mock(Player.class);
        when(storage.getAll()).thenReturn(List.of(first, second));
        MemoryCurrentPlayerStorage currentPlayerStorage = new MemoryCurrentPlayerStorage(storage);
        currentPlayerStorage.change();

        assertThat(currentPlayerStorage.get(), is(first));
        currentPlayerStorage.change();
        assertThat(currentPlayerStorage.get(), is(second));
    }
}