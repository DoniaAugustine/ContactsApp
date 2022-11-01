package com.example.labtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class searchContact extends AppCompatActivity {

    private DBHandler dbHandler;
    private Button showContactList;
    public ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");


        dbHandler = new DBHandler(searchContact.this);

        ListView listView = (ListView) findViewById(R.id.contact_list_search);
        Cursor res = dbHandler.getContactbyName(name);

        while(res.moveToNext()){
            list.add(res.getString(1));
            list.add(res.getString(2));
            list.add(res.getString(3));
            list.add(res.getString(4));
        }

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.contact_list_view, list);
        listView.setAdapter(adapter);

        showContactList = findViewById(R.id.btn_backToContactList);
        showContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(),ContactList.class);
                startActivity(intent);
            }
        });
    }
}