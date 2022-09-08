package com.herve.l5r.system.model;

public class Counter {
    private int count;

    public int getAndIncrement() {
        return count++;
    }
}
