package com.napas.paytouch.di.module;

import android.app.Application;
import android.content.Context;

import com.napas.paytouch.data.AppDataManager;
import com.napas.paytouch.data.DataManager;
import com.napas.paytouch.data.network.ApiHelper;
import com.napas.paytouch.data.network.AppApiHelper;
import com.napas.paytouch.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

}
