package com.example.master.cuciin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    Intent intent;

    public void homeClicked(View view){
        intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
    public void taskClicked(View view){
        intent = new Intent(getApplicationContext(), TaskActivity.class);
        startActivity(intent);
    }
    public void profileClicked(View view) {
        intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
}
