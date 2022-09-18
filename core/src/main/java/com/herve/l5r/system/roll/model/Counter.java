package com.herve.l5r.system.roll.model;

public class Counter {
    private int count;

    public int getAndIncrement() {
        return count++;
    }

    public int value() {
        return count;
    }
}
