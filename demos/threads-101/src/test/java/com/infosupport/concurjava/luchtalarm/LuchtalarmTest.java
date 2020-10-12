package com.infosupport.concurjava.luchtalarm;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LuchtalarmTest {
    private List<Luchtalarm> testset;

    @BeforeEach
    public void setup() {
        testset = List.of(
                new Luchtalarm("Leusden", 15),
                new Luchtalarm("Veenendaal", 15),
                new Luchtalarm("Deurne", 10),
                new Luchtalarm("Rotterdam", 25),
                new Luchtalarm("Antwerpen", 20)
        );
    }

    @Test
    public void testLuchtalarmMetThreads() {
        testset.forEach(l -> new Thread(l::test).start());
    }
}