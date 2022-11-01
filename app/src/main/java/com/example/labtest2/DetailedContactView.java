package com.example.labtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailedContactView extends AppCompatActivity {
    private DBHandler dbHandler;
    private Button updateBtn,deleteBtn;
    public ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_contact_view);

       // Intent intent = getIntent();
        int position = getIntent().getIntExtra("position", 0);


        dbHandler = new DBHandler(DetailedContactView.this);

        ListView listView = (ListView) findViewById(R.id.contact_list);
        Cursor res = dbHandler.getContact(position);

        while(res.moveToNext()){
            list.add(res.getString(1));
            list.add(res.getString(2));
            list.add(res.getString(3));
            list.add(res.getString(4));
        }

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.contact_list_view, list);
        listView.setAdapter(adapter);

        updateBtn = findViewById(R.id.btn_update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(),UpdateContact.class);
                intent.putExtra("position", position);
                intent.putExtra("list",list);
              //  intent.putExtra("name1",list.get(0));
                startActivity(intent);
            }
        });



        deleteBtn = findViewById(R.id.btn_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHandler.deleteContact(position);
//                list.remove(position);
//                adapter.notifyDataSetChanged();

                // after adding the data we are displaying a toast message.
                Toast.makeText(DetailedContactView.this, "Contact has been deleted.", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent();
                intent1.setClass(getBaseContext(),ContactList.class);
                startActivity(intent1);
            }
        });



    }
}