package com.napas.paytouch.data.network;

import com.napas.paytouch.model.Actor;
import com.napas.paytouch.model.BaseResponse;

import io.reactivex.Observable;

public interface ApiHelper {

    Observable<BaseResponse<Actor>> getActors();

}
