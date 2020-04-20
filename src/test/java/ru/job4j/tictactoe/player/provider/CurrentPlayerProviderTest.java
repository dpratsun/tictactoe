package ru.job4j.tictactoe.player.provider;

import org.junit.Test;
import ru.job4j.tictactoe.player.Player;
import ru.job4j.tictactoe.player.provider.impl.CurrentPlayerProvider;
import ru.job4j.tictactoe.player.storage.PlayerStorage;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrentPlayerProviderTest {
    @Test
    public void whenGetThanPlayerShouldBeReturned() {
        PlayerStorage storage = mock(PlayerStorage.class);
        Player first = mock(Player.class);
        when(storage.getAll()).thenReturn(List.of(first));
        CurrentPlayerProvider provider = new CurrentPlayerProvider(storage);

        assertThat(provider.get(), is(first));
    }

    @Test
    public void whenChangeThanGetShouldReturnAnotherPlayer() {
        PlayerStorage storage = mock(PlayerStorage.class);
        Player first = mock(Player.class);
        Player second = mock(Player.class);
        when(storage.getAll()).thenReturn(List.of(first, second));
        CurrentPlayerProvider provider = new CurrentPlayerProvider(storage);

        assertThat(provider.get(), is(first));
        provider.change();
        assertThat(provider.get(), is(second));
    }
}