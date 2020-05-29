package com.example.master.cuciin;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference databaseReference;
    String old_pass, new_pass, re_pass;
    ProgressDialog pd;

    public void confirmPasswordClicked(View view){
        EditText current_password = (EditText) findViewById(R.id.currentPassword);
        EditText new_password = (EditText) findViewById(R.id.newPassword);
        EditText confirm_password = (EditText) findViewById(R.id.confirmPassword);

        old_pass = current_password.getText().toString();
        new_pass = new_password.getText().toString();
        re_pass = confirm_password.getText().toString();

        pd = new ProgressDialog(this);

        if(old_pass.isEmpty() || new_pass.isEmpty() || re_pass.isEmpty()){ //blank field
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
        }
        else if(old_pass.length() < 6 || new_pass.length() < 6){ //password length < 6
            Toast.makeText(this, "Password too short!", Toast.LENGTH_SHORT).show();
        }
        else if(old_pass.matches(new_pass)){ //old pass = new pass
            Toast.makeText(this, "Same passwords", Toast.LENGTH_SHORT).show();
        }
        else if(!new_pass.matches(re_pass)){ //passwords didn't match
            Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
        }
        else{
            user = FirebaseAuth.getInstance().getCurrentUser();
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), old_pass);
            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

            pd.setMessage("Please Wait");
            pd.show();
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Log.i("reauthentication", "Success");
                        user.updatePassword(new_pass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    pd.dismiss();
                                    databaseReference.child("Password").setValue(new_pass);
                                    Log.i("update", "Success");
                                    Toast.makeText(ChangePassword.this, "Password changed", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    pd.dismiss();
                                    Log.i("update","Failed");
                                    Toast.makeText(ChangePassword.this, "Password not changed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        pd.dismiss();
                        Log.i("reauthentication", "Failed");
                        Toast.makeText(ChangePassword.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }
}
