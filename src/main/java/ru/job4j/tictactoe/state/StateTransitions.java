package ru.job4j.tictactoe.state;

import java.util.HashMap;
import java.util.Map;

public class StateTransitions implements StateTransition {
    private final Map<String, State> transitions = new HashMap<>();

    @Override
    public void add(final String key, final State state) {
        transitions.put(key, state);
    }

    @Override
    public State get(final String key) {
        return transitions.getOrDefault(key, null);
    }
}
