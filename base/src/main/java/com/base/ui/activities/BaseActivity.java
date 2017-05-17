package com.base.ui.activities;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.base.ui.common.Layout;

import java.lang.annotation.Annotation;

import activitystarter.ActivityStarter;
import butterknife.ButterKnife;

/**
 * Created by Nick Unuchek (skype: kolyall) on 09.03.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStarter.fill(this);

        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.id());
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ActivityStarter.save(this,outState);
    }

    protected boolean isFinished() {
        return isFinishing() || isDestroyed();
    }
}
