package com.example.dish.ui.forum;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.dish.R;
import com.example.dish.ui.login.LoginData;

import java.util.ArrayList;


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

        ArrayList<String> usernames = LoginData.getInstance().getUsernames();
        String username = usernames.get(usernames.size()-1);
        EditText editText = (EditText) findViewById(R.id.message);
//        EditText nameText = (EditText) findViewById(R.id.name_input);
        LinearLayout display = (LinearLayout) findViewById(R.id.display);
        Button send = (Button) findViewById(R.id.sendb);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String idname = username; // nameText.getText().toString();
                String message = editText.getText().toString();

                LinearLayout temp = new LinearLayout(display.getContext());
                temp.setOrientation(LinearLayout.VERTICAL);
                TextView nameView = new TextView(temp.getContext());
                TextView tv = new TextView(temp.getContext());
                // layout params
                //float factor = this.getContext().getResources().getDisplayMetrics().density;

                // other params
                nameView.setText(idname + ":");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT );
                layoutParams.setMargins(6,6,6,6);
                tv.setText(message);
                tv.setLayoutParams(layoutParams);
                tv.setPadding(15,8,15,8);
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.message_background);
                tv.setTextColor(Color.BLACK);
                tv.setFocusable(true);

                //String prev = display.getText().toString();
                //dh.insertValues(message);
                temp.addView(nameView);
                temp.addView(tv);
                display.addView(temp);

                // clear input
                editText.setText("");
//                nameText.setText("");

                // default scroll to bottom of message history
                final ScrollView scrollview = ((ScrollView) findViewById(R.id.message_history));
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });


    }
}
