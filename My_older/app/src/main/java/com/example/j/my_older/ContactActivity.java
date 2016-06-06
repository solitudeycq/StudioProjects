package com.example.j.my_older;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.j.database.MyDatabaseHelper;

public class ContactActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private ImageView call;
    private ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        dbHelper = new MyDatabaseHelper(this,"My_Older.db",null,1);

        call = (ImageView)findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String[] name = new String[1];
                name[0]=intent.getStringExtra("name");
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from Contacts where name=?",name);
                String phone = "";
                if(cursor.moveToFirst()){
                    do{
                        phone = cursor.getString(cursor.getColumnIndex("phone"));
                    }while (cursor.moveToNext());
                }
                cursor.close();
                Intent call_intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(call_intent);
            }
        });

        delete = (ImageView)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db_delete = dbHelper.getWritableDatabase();
                Intent intent = getIntent();
                String[] name = new String[1];
                name[0]=intent.getStringExtra("name");
                db_delete.execSQL("delete from Contacts where name=?",name);
                Toast.makeText(ContactActivity.this, "Contactor delete", Toast.LENGTH_SHORT).show();
                db_delete.close();
                Intent delete_intent = new Intent();
                delete_intent.setClass(ContactActivity.this,ContactorsActivity.class);
                startActivity(delete_intent);
            }
        });

    }
}
