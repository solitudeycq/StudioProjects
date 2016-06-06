package com.example.solitudeycq.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button save_account;
    private CheckBox rememberPass;

    public static final String PREFS_NAME = "Username";
    public static final String PREFS_KEY = "Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        save_account = (Button)findViewById(R.id.login);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isremember = settings.getBoolean("remember_password",false);
        if(isremember){
            String account = settings.getString("account", "");
            String password = settings.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        save_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                editor = settings.edit();
                if(rememberPass.isChecked()){
                    editor.putBoolean("remember_password", true);
                    Toast.makeText(MainActivity.this,"We have stored your remember choice!",Toast.LENGTH_SHORT).show();
                    editor.putString("account", account);
                    Toast.makeText(MainActivity.this,"We have stored your account!",Toast.LENGTH_SHORT).show();
                    editor.putString("password", password);
                    Toast.makeText(MainActivity.this,"We have stored your password!",Toast.LENGTH_SHORT).show();
                } else {
                    editor.clear();
                    Toast.makeText(MainActivity.this,"We do not store your username and password!",Toast.LENGTH_SHORT).show();
                }
                editor.commit();
            }
        });
    }

    public void save(Context context,String text){
        /*SharedPreferences settings;
        SharedPreferences.Editor editor;*/
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(PREFS_KEY,text);
        editor.commit();
        Toast.makeText(this,"Key:"+text+" saved!",Toast.LENGTH_SHORT).show();
    }
    public String getValue(Context context){
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        text = settings.getString(PREFS_KEY,null);
        return text;
    }
    public void clearSharedPreference(Context context){
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }
    public void removeValue(Context context){
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(PREFS_NAME);
        editor.commit();

    }
    public void saveKey(View view){
        save(this,"this is a test");
    }
    public void getKey(View view){
        String key = getValue(this);
        Toast.makeText(this,"Key stored",Toast.LENGTH_SHORT).show();
    }
}
