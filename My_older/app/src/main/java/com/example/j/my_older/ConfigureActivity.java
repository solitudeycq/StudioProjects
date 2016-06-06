package com.example.j.my_older;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfigureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        Button Btn1 = (Button)findViewById(R.id.button4);//获取按钮资源
        Btn1.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ConfigureActivity.this ,homeActivity.class);
                startActivity(intent);
            }

        });


        TextView Btn2 = (TextView)findViewById(R.id.textView21);//获取按钮资源
        Btn2.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ConfigureActivity.this ,MainActivity.class);
                startActivity(intent);
            }

        });
    }
}
