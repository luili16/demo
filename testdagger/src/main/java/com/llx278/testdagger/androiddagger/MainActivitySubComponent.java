package com.llx278.testdagger.androiddagger;


import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 *
 * Created by liu on 18-5-28.
 */

@Component(modules = AndroidInjectionModule.class)
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }
}
