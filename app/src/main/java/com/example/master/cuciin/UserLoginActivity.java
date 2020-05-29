package com.example.master.cuciin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private FirebaseAuth mAuth;

    private TextView signup_text_view;
    private EditText emails_edit_text;
    private EditText passwords_edit_text;

    ProgressDialog pd;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.signupTextView){
            //click sign-up text
            intent = new Intent(getApplicationContext(), UserSignupActivity.class);
            startActivity(intent);
            finish();
        }
        else if(view.getId() == R.id.logosImageView || view.getId() == R.id.backgroundsLayout){
            //click layout or logo
            InputMethodManager input_method_manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            input_method_manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /*@Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        //change 'enter' to 'down' on keypad
        if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            loginClicked(view);
        }
        return false;
    }*/

    private void loginUser(String email, String password) {
        pd.setMessage("Logging in");
        pd.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    pd.dismiss();
                    Log.i("Login", "Logged in");
                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UserLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginClicked(View view){
        //main button
        String txt_email = emails_edit_text.getText().toString();
        String txt_password = passwords_edit_text.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);

        if(txt_email.isEmpty() || txt_password.isEmpty()){ //blank field
            Toast.makeText(this, "A username and a password are required", Toast.LENGTH_SHORT).show();
        }
        else if(txt_password.length() < 6){ //password length < 6
            Toast.makeText(this, "Password too short!", Toast.LENGTH_SHORT).show();
        }
        else{
            loginUser(txt_email, txt_password);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        //getSupportActionBar().hide();

        signup_text_view = findViewById(R.id.signupTextView);
        signup_text_view.setOnClickListener(this);
        emails_edit_text = findViewById(R.id.emailsEditText);
        passwords_edit_text = findViewById(R.id.passwordsEditText);
        //password_edit_text.setOnKeyListener(this);
        ImageView logos_image_view = findViewById(R.id.logosImageView);
        logos_image_view.setOnClickListener(this);
        RelativeLayout backgrounds_layout = findViewById(R.id.backgroundsLayout);
        backgrounds_layout.setOnClickListener(this);
    }
}
