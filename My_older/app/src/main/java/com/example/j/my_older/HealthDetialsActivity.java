package com.example.j.my_older;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HealthDetialsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_detials);

        Button Btn1 = (Button)findViewById(R.id.button5);//获取按钮资源
        Btn1.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HealthDetialsActivity.this ,homeActivity.class);
                startActivity(intent);
            }

        });
    }
}
