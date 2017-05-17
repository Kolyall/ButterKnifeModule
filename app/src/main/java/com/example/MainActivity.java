package com.example;

import android.os.Bundle;

import com.base.ui.activities.BaseToolbarActivity;
import com.base.ui.common.Layout;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("My title");
    }

}
