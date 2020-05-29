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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserSignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    private TextView login_text_view;
    private EditText name_edit_text;
    private EditText phone_edit_text;
    private EditText email_edit_text;
    private EditText password_edit_text;
    private EditText repassword_edit_text;

    ProgressDialog pd;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.loginTextView){
            //click login text
            intent = new Intent(getApplicationContext(), UserLoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if(view.getId() == R.id.logoImageView || view.getId() == R.id.backgroundLayout){
            //click layout or logo
            InputMethodManager input_method_manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            input_method_manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /*@Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        //change 'enter' to 'down' on keypad
        if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            signUpClicked(view);
        }
        return false;
    }*/

    public void registerUser(final String name, final String phone, final String email, final String password){
        pd.setMessage("Please Wait");
        pd.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.i("Registration", "Success");

                HashMap<String, Object> map = new HashMap<>();
                map.put("UserId", mAuth.getCurrentUser().getUid());
                map.put("Name", name);
                map.put("Phone", phone);
                map.put("Email", email);
                map.put("Password", password);
                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            intent = new Intent(getApplicationContext(), HomeActivity.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Log.i("Registration", "Failed");
                Toast.makeText(UserSignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signupClicked(View view){
        //main button
        String txt_name = name_edit_text.getText().toString();
        String txt_phone = phone_edit_text.getText().toString();
        String txt_email = email_edit_text.getText().toString();
        String txt_password = password_edit_text.getText().toString();
        String txt_repassword = repassword_edit_text.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        pd = new ProgressDialog(this);

        if(txt_name.isEmpty() || txt_phone.isEmpty() || txt_email.isEmpty() || txt_password.isEmpty() || txt_repassword.isEmpty()){ //blank field
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else if (txt_phone.length() < 11 || txt_phone.length() > 12){ //phone number <11 or >12
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
        }
        else if(txt_password.length() < 6){ //password length < 6
            Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
        }
        else if(!txt_password.matches(txt_repassword)){ //password & repassword didn't match
            Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
        }
        else{
            registerUser(txt_name, txt_phone, txt_email, txt_password);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        login_text_view = findViewById(R.id.loginTextView);
        login_text_view.setOnClickListener(this);
        name_edit_text = findViewById(R.id.nameEditText);
        phone_edit_text = findViewById(R.id.phoneEditText);
        //phone_edit_text.setOnKeyListener(this);
        email_edit_text = findViewById(R.id.emailEditText);
        //email_edit_text.setOnKeyListener(this);
        password_edit_text = findViewById(R.id.passwordEditText);
        //password_edit_text.setOnKeyListener(this);
        repassword_edit_text = findViewById(R.id.repasswordEditText);
        //repassword_edit_text.setOnKeyListener(this);
        ImageView logo_image_view = findViewById(R.id.logoImageView);
        logo_image_view.setOnClickListener(this);
        RelativeLayout background_layout = findViewById(R.id.backgroundLayout);
        background_layout.setOnClickListener(this);
    }
}
