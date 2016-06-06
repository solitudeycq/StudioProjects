package com.example.j.my_older;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.j.database.MyDatabaseHelper;

public class ContactorsActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactors_listview);
        dbHelper = new MyDatabaseHelper(this,"My_Older.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Contacts",null,null,null,null,null,null);
        final String[] contactors = new String[cursor.getCount()];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                contactors[i]=cursor.getString(cursor.getColumnIndex("name"));
                i++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ContactorsActivity.this,android.R.layout.simple_list_item_1,contactors);
        ListView listView = (ListView)findViewById(R.id.list_view_contactors);
        listView.setAdapter(adapter);

        Button add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent();
                intent_add.setClass(ContactorsActivity.this,add_contact_activity.class);
                startActivity(intent_add);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String list_name = contactors[i];
                Intent intent = new Intent();
                intent.setClass(ContactorsActivity.this,ContactActivity.class);
                intent.putExtra("name",list_name);
                startActivity(intent);

            }
        });
    }
}
