package com.example.master.cuciin;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    Intent intent;
    TextView onprogress_text_view;
    TextView history_text_view;

    public void homeClicked(View view){
        intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
    public void helpClicked(View view){
        intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }
    public void profileClicked(View view) {
        intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }

    public void backHome(View view){
        homeClicked(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        onprogress_text_view = findViewById(R.id.onprogressTextView);
        history_text_view = findViewById(R.id.historyTextView);

        onprogress_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onprogress_text_view.setTextColor(R.color.common_google_signin_btn_text_dark_default);
                onprogress_text_view.setBackgroundColor(R.color.colorPrimary);
                onprogress_text_view.setPadding(0,25,0,0);
                //history_text_view.setTextColor(R.color.colorPrimary);
                history_text_view.setBackgroundResource(R.drawable.square_border);
                history_text_view.setPadding(0,25,0,0);
            }
        });

        history_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //history_text_view.setTextColor(R.color.common_google_signin_btn_text_dark_default);
                history_text_view.setBackgroundColor(R.color.colorPrimary);
                history_text_view.setPadding(0,25,0,0);
                //onprogress_text_view.setTextColor(R.color.colorPrimary);
                onprogress_text_view.setBackgroundResource(R.drawable.square_border);
                onprogress_text_view.setPadding(0,25,0,0);
            }
        });
    }
}
