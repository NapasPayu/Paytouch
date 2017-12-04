package com.napas.paytouch.ui.main;

import android.text.TextUtils;

import com.napas.paytouch.R;
import com.napas.paytouch.data.DataManager;
import com.napas.paytouch.model.Actor;
import com.napas.paytouch.model.BaseResponse;
import com.napas.paytouch.model.SearchInput;
import com.napas.paytouch.ui.base.BasePresenter;
import com.napas.paytouch.util.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl<V extends MainView> extends BasePresenter<V> implements MainPresenter<V> {

    @Inject
    public MainPresenterImpl(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onInitialize() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().getActors()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableObserver<BaseResponse<Actor>>() {
                    @Override
                    public void onNext(BaseResponse<Actor> baseResponseActor) {
                        getMvpView().setActorList(baseResponseActor.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().showOkDialog(R.string.error, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().hideLoading();
                    }
                }));
    }

    @Override
    public void performSearch(List<Actor> actors, SearchInput searchInput) {
        if (actors == null || actors.isEmpty()) return;

        getMvpView().showLoading();
        final String searchName = searchInput.getName();
        final String searchLocation = searchInput.getLocation();
        final Boolean searchTop = searchInput.getTop();
        final Double searchMinPopularity = searchInput.getMinPopularity();
        final Double searchMaxPopularity = searchInput.getMaxPopularity();

        Observable.fromArray(actors)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(new Function<List<Actor>, List<Actor>>() {
                    @Override
                    public List<Actor> apply(List<Actor> actors) throws Exception {
                        return actors;
                    }
                })
                .filter(new Predicate<Actor>() {
                    @Override
                    public boolean test(Actor actor) throws Exception {
                        Double popularity = actor.getPopularity();
                        if ((TextUtils.isEmpty(searchName) || actor.getName().toLowerCase().contains(searchName.toLowerCase()))
                                && (TextUtils.isEmpty(searchLocation) || actor.getLocation().equalsIgnoreCase(searchLocation))
                                && (searchTop == null || actor.getTop() == searchTop)
                                && (popularity >= searchMinPopularity && popularity <= searchMaxPopularity)) {
                            return true;
                        }
                        return false;
                    }
                })
                .toList()
                .subscribe(new Consumer<List<Actor>>() {
                    @Override
                    public void accept(List<Actor> actors) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().setActorList(actors);
                    }
                });
    }
}
