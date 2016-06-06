package com.example.j.my_older;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.j.database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private EditText account;
    private EditText password;
    private CheckBox rememberPass;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建数据库
        dbHelper = new MyDatabaseHelper(this,"My_Older.db",null,1);
        dbHelper.getWritableDatabase();

        account = (EditText)findViewById(R.id.account);
        password = (EditText)findViewById(R.id.password);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        //判断是否记住密码
        boolean isRemember = settings.getBoolean("remember_password",false);
        if(isRemember){
            account.setText(settings.getString("account",""));
            password.setText(settings.getString("password",""));
            rememberPass.setChecked(true);
        }
        //login
        Button login = (Button)findViewById(R.id.login);//获取按钮资源
        login.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                String temaccount = account.getText().toString();
                String tempassword = password.getText().toString();
                String[] args = new String[2];
                args[0] = temaccount;
                args[1] = tempassword;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from Users where username=? and password=?",args);
                if((cursor.getCount())!=0) { //checkout wherther the users login successful
                    editor = settings.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", temaccount);
                        editor.putString("password", tempassword);
                        Toast.makeText(MainActivity.this, "We have stored your accountInfo!", Toast.LENGTH_SHORT).show();
                    } else {
                        editor.clear();
                        Toast.makeText(MainActivity.this, "We do not store your username and password!", Toast.LENGTH_SHORT).show();
                    }
                    editor.commit();
                    Intent successIntent = new Intent();
                    successIntent.setClass(MainActivity.this,homeActivity.class);
                    startActivity(successIntent);
                    cursor.close();
                    dbHelper.close();
                }else{
                    Toast.makeText(MainActivity.this,"Your account or your password is incorrect!",Toast.LENGTH_SHORT).show();
                    cursor.close();
                    dbHelper.close();
                }
            }
        });

        //Register
        Button register = (Button)findViewById(R.id.register);//获取按钮资源
        register.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this ,ResignActivity.class);
                startActivityForResult(intent,1);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    account.setText(data.getStringExtra("username"));
                    password.setText(data.getStringExtra("password"));
                }
        }
    }
}
