package com.infosupport.concurjava.luchtalarm;

import java.util.List;

public class LuchtalarmThreadTest {
    public static void main(String[] args) {
        var testset = List.of(
                new Luchtalarm("Leusden", 15),
                new Luchtalarm("Veenendaal", 15),
                new Luchtalarm("Deurne", 10),
                new Luchtalarm("Rotterdam", 25),
                new Luchtalarm("Antwerpen", 20));

        testset.forEach(l -> new Thread(l::test).start());
    }
}
