package com.napas.paytouch.data.network;

import com.google.gson.reflect.TypeToken;
import com.napas.paytouch.model.Actor;
import com.napas.paytouch.model.BaseResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<BaseResponse<Actor>> getActors() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ACTORS)
                .build()
                .getParseObservable(new TypeToken<BaseResponse<Actor>>() {
                });
    }
}

