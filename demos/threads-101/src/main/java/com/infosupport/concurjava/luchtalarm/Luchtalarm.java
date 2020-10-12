package com.infosupport.concurjava.luchtalarm;

import java.awt.*;
import java.util.stream.IntStream;

public class Luchtalarm {
    private String plaats;
    private int testduurInSeconden;

    public Luchtalarm(String plaats, int testduurInSeconden) {
        this.plaats = plaats;
        this.testduurInSeconden = testduurInSeconden;
    }

    public void test() {
        IntStream.rangeClosed(1, testduurInSeconden)
                .forEach(huidigeSeconde ->{
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println(String.format("#luchtalarm! Test in %s; seconde %d van %d",
                            plaats,
                            huidigeSeconde,
                            testduurInSeconden));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    public String getPlaats() {
        return plaats;
    }
}
