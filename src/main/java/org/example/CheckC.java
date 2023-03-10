package org.example;

import java.util.concurrent.Callable;

public class CheckC implements Callable {
    private final String text;
    private int maxA = 0;

    CheckC(String text) {
        this.text = text;
    }

    @Override
    public Integer call() throws Exception {
        for (char charC : text.toCharArray()) {
            if (charC == 'c') { maxA++;}
        }
        return maxA;
    }
}
