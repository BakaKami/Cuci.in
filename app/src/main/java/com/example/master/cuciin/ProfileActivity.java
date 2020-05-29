package com.example.master.cuciin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends AppCompatActivity {

    Intent intent;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference mDatabaseUser_name, mDatabaseUser_phone, mDatabaseUser_email; //reference;
    StorageReference storageReference;

    //private static final int IMAGE_REQUEST = 1;
    public static final int TAKE_IMAGE_CODE = 10001;
    public static final String TAG = "TAG";
    //private Uri image_uri;
    //private StorageTask upload_task;

    EditText current_name_edit_text;
    EditText current_phone_edit_text;
    EditText current_email_edit_text;
    CircleImageView profile_image;

    public void changePasswordClicked(View view){
        intent = new Intent(getApplicationContext(), ChangePassword.class);
        startActivity(intent);
    }

    public void logOut(View view){
        //Button log_out_button = (Button) findViewById(R.id.logOutButton);
        FirebaseAuth.getInstance().signOut();
        Log.i("Log out","Success");
        intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void homeClicked(View view){
        intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
    public void taskClicked(View view) {
        intent = new Intent(getApplicationContext(), TaskActivity.class);
        startActivity(intent);
    }
    public void helpClicked(View view){
        intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }

    /*private void uploadImage(){
        final ProgressDialog pd = new ProgressDialog(getApplicationContext());
        pd.setMessage("Uploading");
        pd.show();

        if(image_uri != null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(image_uri));

            upload_task = fileReference.putFile(image_uri);
            upload_task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURl", mUri);
                        reference.updateChildren(map);

                        pd.dismiss();
                    }
                    else{
                        Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        }
        else{
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mimeTypeMap =  MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void openImage(){
        intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && requestCode == RESULT_OK && data != null && data.getData() != null){
            image_uri = data.getData();

            if(upload_task != null &&  upload_task.isInProgress()){
                Toast.makeText(this, "Upload in progress", Toast.LENGTH_SHORT).show();
            }
            else{
                uploadImage();
            }
        }
    }*/

    private void setUserProfileUrl(Uri uri){
        user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setPhotoUri(uri).build();

        user.updateProfile(request).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ProfileActivity.this, "Profile image updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "Failed to update profile image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDownloadUrl(StorageReference storageReference){
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d(TAG, "onSuccess: " + uri);
                setUserProfileUrl(uri);
            }
        });
    }

    private void handleUpload(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference().child("profileImages").child(uid + ".jpeg");
        storageReference.putBytes(baos.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                getDownloadUrl(storageReference);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "On Failure", e.getCause());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TAKE_IMAGE_CODE){
            switch (resultCode){
                case RESULT_OK:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    profile_image.setImageBitmap(bitmap);
                    handleUpload(bitmap);
            }
        }
    }

    public void handleImageClick(View view){
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, TAKE_IMAGE_CODE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_image = findViewById(R.id.profileImage);
        current_name_edit_text = findViewById(R.id.currentNameEditText);
        current_phone_edit_text = findViewById(R.id.currentPhoneEditText);
        current_email_edit_text = findViewById(R.id.currentEmailEditText);

        if(mAuth.getInstance().getCurrentUser() != null){
            String user_id = mAuth.getInstance().getCurrentUser().getUid();
            mDatabaseUser_name = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Name");
            mDatabaseUser_phone = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Phone");
            mDatabaseUser_email = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Email");

            mDatabaseUser_name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    current_name_edit_text.setText(dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mDatabaseUser_phone.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    current_phone_edit_text.setText(dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mDatabaseUser_email.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    current_email_edit_text.setText(dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            //no user detected
            Log.i("Info", "null");
        }

        /*storageReference = FirebaseStorage.getInstance().getReference("Uploads");

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });*/

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Log.d(TAG, "OnCreate: " + user.getDisplayName());
            if(user.getPhotoUrl() != null){
                Glide.with(this).load(user.getPhotoUrl()).into(profile_image);
            }
        }
    }
}
