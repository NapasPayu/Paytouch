/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.napas.paytouch.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.napas.paytouch.di.component.ActivityComponent;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        hideLoading();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void showOkDialog(String title, String message) {
        if (mActivity != null) {
            mActivity.showOkDialog(title, message);
        }
    }

    @Override
    public void showOkDialog(@StringRes int titleResId, @StringRes int messageResId) {
        if (mActivity != null) {
            mActivity.showOkDialog(titleResId, messageResId);
        }
    }

    @Override
    public void showOkDialog(@StringRes int titleResId, String message) {
        if (mActivity != null) {
            mActivity.showOkDialog(titleResId, message);
        }
    }


    public ActivityComponent getActivityComponent() {
        return mActivity.getActivityComponent();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    public void startActivity(Intent intent) {
        if (mActivity != null) {
            mActivity.startActivity(intent);
        }
    }
}
