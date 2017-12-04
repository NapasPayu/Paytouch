package com.napas.paytouch.model;

import java.util.List;

public class BaseResponse<T> {

    List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
