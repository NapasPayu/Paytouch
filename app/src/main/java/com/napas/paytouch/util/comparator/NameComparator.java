package com.napas.paytouch.util.comparator;

import com.napas.paytouch.model.Actor;

import java.util.Comparator;

public class NameComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor actor1, Actor actor2) {
        String name1 = actor1.getName();
        String name2 = actor2.getName();
        return name1.compareToIgnoreCase(name2);
    }
}
