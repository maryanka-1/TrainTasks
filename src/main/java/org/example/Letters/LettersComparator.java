package org.example.Letters;

import java.util.Comparator;

public class LettersComparator implements Comparator<Main.Letter> {
    @Override
    public int compare(Main.Letter o1, Main.Letter o2) {
        return o1.getDateReceived().compareTo(o2.getDateReceived());
    }
}

