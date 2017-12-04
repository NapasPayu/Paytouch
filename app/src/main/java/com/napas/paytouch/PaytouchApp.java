package com.napas.paytouch;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.napas.paytouch.di.component.ApplicationComponent;
import com.napas.paytouch.di.component.DaggerApplicationComponent;
import com.napas.paytouch.di.module.ApplicationModule;

public class PaytouchApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);

        AndroidNetworking.initialize(getApplicationContext());
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}