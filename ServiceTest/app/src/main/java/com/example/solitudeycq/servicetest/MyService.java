package com.example.solitudeycq.servicetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by solitudeycq on 16/6/6.
 */
public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d(TAG, "startDownload: executed");
        }

        public int getProgress(){
            Log.d(TAG, "getProgress: executed");
            return 0;
        }
    }
    private static final String TAG = "MyService";
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
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

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pi);

        Notification notification = builder.getNotification();
        notification.defaults = Notification.DEFAULT_ALL;
        startForeground(1,notification);
        Log.d(TAG, "onCreate: executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}
