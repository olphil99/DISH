package com.example.dish.ui.forum;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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
        TextView subscribe = (TextView) findViewById(R.id.subscribe);
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

        TextView send = (TextView) findViewById(R.id.sendb);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.message);
                EditText name = (EditText) findViewById(R.id.id);
                String idname = name.getText().toString();
                String message = editText.getText().toString();
                LinearLayout display = (LinearLayout) findViewById(R.id.display);

                LinearLayout temp = new LinearLayout(display.getContext());
                temp.setOrientation(LinearLayout.VERTICAL);
                TextView nameView = new TextView(temp.getContext());
                TextView tv = new TextView(temp.getContext());
                // layout params
                //float factor = this.getContext().getResources().getDisplayMetrics().density;

                // other params
                nameView.setText(idname + ":");
                display.setMinimumWidth(nameView.getWidth());

                float factor = display.getContext().getResources().getDisplayMetrics().density;
                tv.setText(message);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT );
                layoutParams.leftMargin = (int)(15* factor);
                layoutParams.rightMargin =(int)(15* factor);
                tv.setLayoutParams(layoutParams);
                tv.setPadding(5,2,5,2);
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.back);
                tv.setTextColor(Color.BLACK);
                tv.setFocusable(true);

                //String prev = display.getText().toString();
                //dh.insertValues(message);
                temp.addView(nameView);
                temp.addView(tv);
                display.addView(temp);
            }
        });


    }
}
