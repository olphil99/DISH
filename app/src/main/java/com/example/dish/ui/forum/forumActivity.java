package com.example.dish.ui.forum;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.dish.R;


public class forumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_activity);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        TextView fName = (TextView) findViewById(R.id.forumName);
        fName.setText(name);
        Button subscribe = (Button) findViewById(R.id.subscribe);
        subscribe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String current = subscribe.getText().toString();
                if(current == "subscribe"){
                    //subscribe action
                    subscribe.setText("unsubscribe");
                }else{
                    //unsubscribe action
                    subscribe.setText("subscribe");
                }
            }
        });

        Button send = (Button) findViewById(R.id.sendb);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.message);
                EditText name = (EditText) findViewById(R.id.id);
                String idname = name.getText().toString();
                String message = editText.getText().toString();
                TextView display = (TextView) findViewById(R.id.display);
                String prev = display.getText().toString();
                //dh.insertValues(message);
                display.setText(prev + "\n \n" + idname + ":" + "\n\n" + message);
            }
        });


    }
}
