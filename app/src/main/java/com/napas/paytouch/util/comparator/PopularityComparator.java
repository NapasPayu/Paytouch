package com.napas.paytouch.util.comparator;

import com.napas.paytouch.model.Actor;

import java.util.Comparator;

public class PopularityComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor actor1, Actor actor2) {
        Double popularity1 = actor1.getPopularity();
        Double popularity2 = actor2.getPopularity();
        return popularity1.compareTo(popularity2);
    }
}
