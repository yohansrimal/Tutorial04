package com.example.tutorial04;


import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorial04.Database.DBHelper;


public class MainActivity extends AppCompatActivity {

    Button select_all, add, delete, update, sign_in;
    EditText username, password;
    DBHelper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDb = new DBHelper(this);
        SQLiteDatabase db = userDb.getReadableDatabase();

        select_all = findViewById(R.id.selectAll_btn);
        sign_in = findViewById(R.id.signIn_btn);
        add = findViewById(R.id.add_btn);
        update = findViewById(R.id.update_btn);
        delete = findViewById(R.id.delete_btn);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.password);

    }

    @Override
    protected void onResume() {
        super.onResume();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long status = userDb.addInfo(username.getText().toString(), password.getText().toString());
                if (status > 0) {
                    Toast.makeText(MainActivity.this, "New user added successfully ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User can not be added", Toast.LENGTH_SHORT).show();
                }


            }
        });

        select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (Object name : userDb.readAllInfo()) {

                    String value = (String) name;
                    Log.i("Username", value);
                }
            }
        });


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userDb.readInfo(username.getText().toString(), password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "User signed in", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User can not found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDb.updateInfo(username.getText().toString(), password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "User updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDb.deleteInfo(username.getText().toString())) {
                    Toast.makeText(MainActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
