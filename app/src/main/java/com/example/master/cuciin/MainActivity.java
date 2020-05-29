package com.example.master.cuciin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    public void getStarted(View view){
        Switch user_type_switch = (Switch) findViewById(R.id.userTypeSwitch);
        Log.i("Switch value", String.valueOf(user_type_switch.isChecked()));

        if(user_type_switch.isChecked()){
            //admin side
            Log.i("Identification","Admin");
            Toast.makeText(this, "Not available yet", Toast.LENGTH_SHORT).show();
        }
        else{
            //user side
            Log.i("Identification","User");
            intent = new Intent(getApplicationContext(), UserSignupActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
    }
}
