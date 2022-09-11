package com.herve.l5r.system;

import java.util.Random;

public class Test  {
    public static void main(String[] args) {
        Random random = new Random(100);
        System.out.println(random.nextInt(10) + 1);
    }
}
