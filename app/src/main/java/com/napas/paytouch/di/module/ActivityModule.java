package com.napas.paytouch.di.module;

import android.app.Activity;
import android.content.Context;

import com.napas.paytouch.di.ActivityContext;
import com.napas.paytouch.di.PerActivity;
import com.napas.paytouch.ui.main.MainPresenter;
import com.napas.paytouch.ui.main.MainPresenterImpl;
import com.napas.paytouch.ui.main.MainView;
import com.napas.paytouch.util.rx.AppSchedulerProvider;
import com.napas.paytouch.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainPresenter<MainView> provideMainPresenter(MainPresenterImpl<MainView> presenter) {
        return presenter;
    }
}
