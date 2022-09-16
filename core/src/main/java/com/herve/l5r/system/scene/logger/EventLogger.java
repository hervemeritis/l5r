package com.herve.l5r.system.scene.logger;

import java.util.ArrayList;
import java.util.List;

public class EventLogger {
    private final List<Event> events = new ArrayList<>();

    public void log(Event event) {
        events.add(event);
    }

    public void print() {
        events.forEach(event -> System.out.println(event.message()));
    }
}
