package com.example.labtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateContact extends AppCompatActivity {

    private EditText name,phone,email,address;
    private Button updateBtn;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);


        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        ArrayList arrayList = intent.getStringArrayListExtra("list");
      //  String getName = intent.getStringExtra("name1");
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        updateBtn = findViewById(R.id.btn_update);

        dbHandler = new DBHandler(UpdateContact.this);

        name.setText(arrayList.get(0).toString());
        phone.setText(arrayList.get(1).toString());
        email.setText(arrayList.get(2).toString());
        address.setText(arrayList.get(3).toString());

       // name.setText(getName);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contactName = name.getText().toString();
                String contactPhone = phone.getText().toString();
                String contactEmail = email.getText().toString();
                String contactAddress = address.getText().toString();

                // validating if the text fields are empty or not.
                if (contactName.isEmpty() && contactPhone.isEmpty() && contactEmail.isEmpty() && contactAddress.isEmpty()) {
                    Toast.makeText(UpdateContact.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if(flg == "add") {
                //add new contact to sqlite data and pass all values to it.
 //               dbHandler.addContact(contactName, contactPhone, contactEmail, contactAddress);
                dbHandler.updateContact(position,contactName, contactPhone, contactEmail, contactAddress);
                // after adding the data we are displaying a toast message.
                Toast.makeText(UpdateContact.this, "Contact has been updated.", Toast.LENGTH_SHORT).show();
                name.setText("");
                phone.setText("");
                email.setText("");
                address.setText("");
//                }
//                else{
//
//
//                }
                Intent intent = new Intent();
                intent.setClass(getBaseContext(),ContactList.class);
                startActivity(intent);
            }
        });
    }
}