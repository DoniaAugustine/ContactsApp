package com.example.labtest2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class ContactList extends AppCompatActivity {

    private DBHandler dbHandler;
    private Button contactBtn,searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

       dbHandler = new DBHandler(ContactList.this);
        ArrayList<String> list = new ArrayList<String>();
        ListView listView = (ListView) findViewById(R.id.contact_list);
       Cursor res = dbHandler.viewContacts();

        while(res.moveToNext()){
            list.add(res.getString(1));

        }

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.contact_list_view, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {


                Intent intent = new Intent();
                intent.setClass(getBaseContext(), DetailedContactView.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        contactBtn = findViewById(R.id.btn_contact);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(),AddContact.class);

                startActivity(intent);
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        searchBtn = findViewById(R.id.btn_search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert.setTitle("Enter the name to search");

                alert.setView(edittext);

                alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String EditTextValue = edittext.getText().toString();
                        Intent intent = new Intent();
                intent.setClass(getBaseContext(),searchContact.class);

                  intent.putExtra("name",EditTextValue);

                startActivity(intent);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

            }
        });

    }

}