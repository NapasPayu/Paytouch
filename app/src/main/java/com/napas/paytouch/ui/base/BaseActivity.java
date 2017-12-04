package com.napas.paytouch.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.napas.paytouch.PaytouchApp;
import com.napas.paytouch.R;
import com.napas.paytouch.di.component.ActivityComponent;
import com.napas.paytouch.di.component.DaggerActivityComponent;
import com.napas.paytouch.di.module.ActivityModule;
import com.napas.paytouch.util.CommonUtil;

import butterknife.Unbinder;

public class BaseActivity extends AppCompatActivity implements MvpView {

    private ProgressDialog mProgressDialog;
    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((PaytouchApp) getApplication()).getComponent())
                .build();
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        hideLoading();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            this.finish();
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtil.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showOkDialog(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null);
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void showOkDialog(@StringRes int titleResId, @StringRes int messageResId) {
        showOkDialog(getString(titleResId), getString(messageResId));
    }

    @Override
    public void showOkDialog(@StringRes int titleResId, String message) {
        showOkDialog(getString(titleResId), message);
    }

    public void addFragment(@NonNull int containerViewId, @NonNull Fragment fragment) {
        if (fragment == null) return;
        getSupportFragmentManager().beginTransaction()
                .add(containerViewId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragment(@NonNull int containerViewId, @NonNull Fragment fragment) {
        if (fragment == null) return;
        getSupportFragmentManager().beginTransaction()
                .replace(containerViewId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }
}
