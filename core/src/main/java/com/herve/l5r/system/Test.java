package com.herve.l5r.system;

import com.herve.l5r.system.sample.SamuraiFactory;

import java.util.Random;

public class Test  {
    public static void main(String[] args) {
        System.out.println(SamuraiFactory.matsu().generateDamageBonus());
    }
}
