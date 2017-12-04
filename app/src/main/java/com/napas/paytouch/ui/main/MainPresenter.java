package com.napas.paytouch.ui.main;

import com.napas.paytouch.di.PerActivity;
import com.napas.paytouch.model.Actor;
import com.napas.paytouch.model.SearchInput;
import com.napas.paytouch.ui.base.MvpPresenter;

import java.util.List;

@PerActivity
public interface MainPresenter<V extends MainView> extends MvpPresenter<V> {

    void onInitialize();

    void performSearch(List<Actor> actors, SearchInput searchInput);
}
