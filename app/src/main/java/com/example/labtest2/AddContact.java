package com.example.labtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    private EditText name,phone,email,address;
    private Button addBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


        //initializing variables
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        addBtn = findViewById(R.id.btn_add);

        //create new dbhandler class and passing the context to it
        dbHandler = new DBHandler(AddContact.this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contactName = name.getText().toString();
                String contactPhone = phone.getText().toString();
                String contactEmail = email.getText().toString();
                String contactAddress = address.getText().toString();

                // validating if the text fields are empty or not.
                if (contactName.isEmpty() && contactPhone.isEmpty() && contactEmail.isEmpty() && contactAddress.isEmpty()) {
                    Toast.makeText(AddContact.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }


                    //add new contact to sqlite data and pass all values to it.
                    dbHandler.addContact(contactName, contactPhone, contactEmail, contactAddress);

                    // after adding the data we are displaying a toast message.
                    Toast.makeText(AddContact.this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    phone.setText("");
                    email.setText("");
                    address.setText("");

                Intent intent = new Intent();
                intent.setClass(getBaseContext(),ContactList.class);
                startActivity(intent);
      }
        });


    }

}