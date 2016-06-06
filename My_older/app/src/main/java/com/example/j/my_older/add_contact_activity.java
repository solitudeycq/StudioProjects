package com.example.j.my_older;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.j.database.MyDatabaseHelper;

public class add_contact_activity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText name;
    private EditText phone;
    private EditText remarks;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_activity);

        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        remarks = (EditText)findViewById(R.id.remarks);

        dbHelper = new MyDatabaseHelper(this,"My_Older.db",null,1);

        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact_name = name.getText().toString();
                String contact_phone = phone.getText().toString();
                String contack_remarks = remarks.getText().toString();
                if(contact_name.equals("")||contact_phone.equals("")||contack_remarks.equals("")){
                    Toast.makeText(add_contact_activity.this,"Null exist!",Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name",contact_name);
                    values.put("phone",contact_phone);
                    values.put("remarks",contack_remarks);
                    db.insert("Contacts",null,values);
                    values.clear();
                    dbHelper.close();
                    Toast.makeText(add_contact_activity.this,"Store Succeeded",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(add_contact_activity.this,ContactorsActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}
