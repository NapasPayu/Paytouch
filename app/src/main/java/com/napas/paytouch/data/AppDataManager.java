package com.napas.paytouch.data;

import android.content.Context;

import com.napas.paytouch.data.network.ApiHelper;
import com.napas.paytouch.di.ApplicationContext;
import com.napas.paytouch.model.Actor;
import com.napas.paytouch.model.BaseResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<BaseResponse<Actor>> getActors() {
        return mApiHelper.getActors();
    }
}
