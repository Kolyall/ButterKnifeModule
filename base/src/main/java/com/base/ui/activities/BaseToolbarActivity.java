/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.base.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.base.ui.R;
import com.base.ui.R2;

import butterknife.BindView;
import lombok.Getter;
import lombok.experimental.Accessors;
@Accessors(prefix = "m")
public abstract class BaseToolbarActivity extends BaseActivity {
    public static final String TAG = BaseToolbarActivity.class.getSimpleName();


    @Nullable @Getter
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @Nullable
    @BindView(R2.id.toolbar_title)
    TextView mToolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeToolbar();
    }

    protected void initializeToolbar() {
        if (mToolbar == null) {
            Log.e(TAG, "initializeToolbar: ", new Exception("Toolbar is null"));
            return;
        }
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_left);
            actionBar.setHomeActionContentDescription(getString(R.string.go_back));
//            mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    protected void showBackButtonOnToolbar(boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show);
            actionBar.setDisplayShowHomeEnabled(show);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_left);
            actionBar.setHomeActionContentDescription(getString(R.string.go_back));
            getSupportActionBar().setHomeButtonEnabled(show);
        }
//        if (mToolbar==null) return;
//        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    public void hideHomeAsUp() {
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void setTitle(@StringRes int titleResId) {
        if (titleResId <= 0) return;

        setTitle(getString(titleResId));
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        }
    }

}
