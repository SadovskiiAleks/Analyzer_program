package org.example;

import java.util.concurrent.Callable;

public class CheckB implements Callable {
    private final String text;
    private int maxA = 0;

    CheckB(String text) {
        this.text = text;
    }

    @Override
    public Integer call() throws Exception {
        for (char charB : text.toCharArray()) {
            if (charB == 'b') { maxA++;}
        }
        return maxA;
    }
}
