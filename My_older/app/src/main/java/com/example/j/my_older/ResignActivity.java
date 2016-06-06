package com.example.j.my_older;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.j.database.MyDatabaseHelper;

public class ResignActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText editText_username;
    private EditText editText_password;
    private EditText editText_repassword;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);

        editText_username = (EditText)findViewById(R.id.username);
        editText_password = (EditText)findViewById(R.id.password);
        editText_repassword = (EditText)findViewById(R.id.repassword);

        dbHelper = new MyDatabaseHelper(this,"My_Older.db",null,1);

        register = (Button)findViewById(R.id.register);//获取按钮资源
        register.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();
                String repassword = editText_repassword.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                if(username.equals("")){
                    Toast.makeText(ResignActivity.this,"Please enter your username!",Toast.LENGTH_SHORT).show();
                }else if(username.equals("yang")){ //checkout if the username is availiable
                    Toast.makeText(ResignActivity.this,"The username is exist!",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(ResignActivity.this,"Sorry,your password is empty!",Toast.LENGTH_SHORT).show();
                }else if(!(password.equals(repassword))){
                    Toast.makeText(ResignActivity.this,"Two passwords do not match!",Toast.LENGTH_SHORT).show();
                }else{
                    values.put("username",username);
                    values.put("password",password);
                    db.insert("Users",null,values);
                    values.clear();
                    dbHelper.close();
                    Toast.makeText(ResignActivity.this,"Store your account-info succeeded",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent();
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }


}
