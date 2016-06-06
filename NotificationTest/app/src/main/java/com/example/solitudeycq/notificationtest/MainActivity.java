package com.example.solitudeycq.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_notice:
                //1,得到一个NotificationManager对象
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                //2,实例化Notification
                Notification.Builder builder = new Notification.Builder(this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setTicker("Hello");
                builder.setWhen(System.currentTimeMillis());
                //定义notification消息和PendingIntent
                builder.setContentTitle("My notification");
                builder.setContentText("Hello world!");

                Intent intent = new Intent(this,NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setContentIntent(pi);

                Notification notification = builder.getNotification();
                notification.defaults = Notification.DEFAULT_ALL;
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
