package com.georg.qulextestapp.view;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.georg.qulextestapp.QulexTestApplication;
import com.georg.qulextestapp.injection.ApplicationComponent;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    public ApplicationComponent getAppComponent() {
        return QulexTestApplication.get(this).getComponent();
    }

    protected BaseActivity getActivity() {
        return this;
    }

}
