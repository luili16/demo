package com.llx278.testdagger.androiddagger;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liu on 18-5-29.
 */

public class MainPresenter {

    @Inject
    public MainPresenter() {
    }

    public void method1() {
        Log.d("main","method1 in MainPresenter");
    }
}
