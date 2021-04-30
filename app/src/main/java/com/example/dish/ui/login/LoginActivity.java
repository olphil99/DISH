package com.example.dish.ui.login;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dish.MainActivity;
import com.example.dish.R;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = new User(username.getText().toString(), password.getText().toString());
                LoginData.getInstance().addUser(user);
                Toast.makeText(getBaseContext(), "login successful ^_^", Toast.LENGTH_SHORT).show();
//                if (LoginData.getInstance().addUser(user)) {
//                    Toast.makeText(getBaseContext(), "login successful ^_^", Toast.LENGTH_SHORT).show();
//                    Intent mainActivityIntent = new Intent(v.getContext(), MainActivity.class);
//                    v.getContext().startActivity(mainActivityIntent);
//                } else {
//                    Toast.makeText(getBaseContext(), "login failed ^w^", Toast.LENGTH_SHORT).show();
//                }
                SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = pref.edit();
                edt.putBoolean("activity_executed", true);
                edt.commit();

            }
        });
    }
}




