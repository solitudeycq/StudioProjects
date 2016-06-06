package com.example.j.my_older;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//Contactors
        ImageView Btn1 = (ImageView)findViewById(R.id.imageView4);//获取按钮资源
        Btn1.setOnClickListener(new ImageView.OnClickListener(){//创建监听
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(homeActivity.this ,ContactorsActivity.class);
                startActivity(intent);
            }

        });
        //Configure
        ImageView Btn2 = (ImageView)findViewById(R.id.imageView5);//获取按钮资源
        Btn2.setOnClickListener(new ImageView.OnClickListener(){//创建监听
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(homeActivity.this ,ConfigureActivity.class);
                startActivity(intent);
            }

        });
        //health Detials
        TextView Btn3 = (TextView)findViewById(R.id.textView7);//获取按钮资源
        Btn3.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(homeActivity.this ,HealthDetialsActivity.class);
                startActivity(intent);
            }

        });
    }
}
