package com.example.solitudeycq.broadcastbestpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by solitudeycq on 16/5/25.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removedActivity(this);
    }
}
