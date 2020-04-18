package ru.job4j.tictactoe.state;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StateTransitionsTest {
    private StateTransition transition;

    @Before
    public void setUp() {
        transition = new StateTransitions();
    }

    @Test
    public void whenGetStateWhichWasAddedThanStateShouldBeReturned() {
        State state = () -> null;
        transition.add("test", state);
        assertThat(transition.get("test"), is(state));
    }

    @Test
    public void whenGetStateWhichWasNotAddedThanNullShouldBeReturned() {
        assertNull(transition.get("test"));
    }
}