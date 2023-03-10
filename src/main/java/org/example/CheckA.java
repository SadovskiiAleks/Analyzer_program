package org.example;

import java.util.concurrent.Callable;

public class CheckA implements Callable {

    private final String text;
    private int maxA = 0;

    CheckA(String text) {
        this.text = text;
    }

    @Override
    public Integer call() throws Exception {
        for (char charA : text.toCharArray()) {
            if (charA == 'a') { maxA++;}
        }
        return maxA;
    }
}
