package com.llx278.testdagger.androiddagger;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.llx278.testdagger.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 *
 * Created by liu on 18-5-28.
 */

public class MainActivity extends Activity {

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.method1();
    }
}
