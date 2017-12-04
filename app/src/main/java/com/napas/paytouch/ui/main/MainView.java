package com.napas.paytouch.ui.main;

import com.napas.paytouch.model.Actor;
import com.napas.paytouch.ui.base.MvpView;

import java.util.Comparator;
import java.util.List;


public interface MainView extends MvpView {

    void setActorList(List<Actor> actors);

    void showActorDetail(Actor actor);

    void showSoredActors(List<Actor> actors, Comparator<Actor> comparator);

    void openSearchActivity();
}
