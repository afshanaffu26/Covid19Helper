package com.example.covid19helper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtVerifyUser;
    EditText editName;
    Button btnSave;
    ImageView imgUser;
    Uri uriProfileImage;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    private static final int CHOOSE_IMAGE = 101;
    String profileImageUrl=null;
    String displayName= null;
    private static final String TAG = "ProfileActivity";
    int TAKE_IMAGE_CODE = 10001;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editName = (EditText) findViewById(R.id.editDisplayName);
        btnSave = findViewById(R.id.btnSave);
        imgUser = findViewById(R.id.imageUserProfile);
        progressBar = findViewById(R.id.progressbar);
        txtVerifyUser=findViewById(R.id.txtVerifyUser);

        btnSave.setOnClickListener(this);
        imgUser.setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        loadUserInformation();
    }

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null) {

           if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl()).into(imgUser);
            }
           if (user.getDisplayName()!=null) {
               String displayName = user.getDisplayName();
               editName.setText(displayName);
               editName.setSelection(user.getDisplayName().length());
           }
           if (user.isEmailVerified())
           {
               txtVerifyUser.setText("Email Verified");
           }
           else
           {
               txtVerifyUser.setText("Email Not Verified(Click to verify)");
               txtVerifyUser.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               Toast.makeText(ProfileActivity.this,"Verification Email Sent",Toast.LENGTH_SHORT).show();
                           }
                       });
                   }
               });
           }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveUserInfoUpdateName();
                break;
            case R.id.imageUserProfile:
                showImageChooser();
                //handleImageClick();
                break;
        }
    }
    private void saveUserInfoUpdateName() {

        displayName=editName.getText().toString();
//        if(displayName.isEmpty()){
//            editName.setError("Name required");
//            editName.requestFocus();
//            return;
//        }
        btnSave.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build();
        firebaseUser.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        btnSave.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Profile name updated successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        btnSave.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void handleImageClick() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent,TAKE_IMAGE_CODE);
        }
    }

//Upload from Camera
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_IMAGE_CODE){
            switch (resultCode)
            {
                case RESULT_OK:
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgUser.setImageBitmap(bitmap);
                handleUpload(bitmap);
            }
        }
    }*/

    private void handleUpload(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final StorageReference reference = FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(uid+".jpeg");
        reference.putBytes(baos.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        getDownloadUrl(reference);
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG,"onFailure",e.getCause());
            }
        });
    }

    private void getDownloadUrl(StorageReference reference) {
        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d(TAG, "onSuccess: "+uri);
                        setUserProfileUrl(uri);
                    }
                });
    }

    private void setUserProfileUrl(Uri uri) {
        FirebaseUser user = mAuth.getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();
        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Profile image failed...", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void showImageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Profile Image"), CHOOSE_IMAGE);
    }
    //Upload from Storage
       @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                uriProfileImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                    imgUser.setImageBitmap(bitmap);
                    uploadImageToFirebaseStorage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    private void uploadImageToFirebaseStorage() {
        //final StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final StorageReference profileImageRef = FirebaseStorage.getInstance().getReference()
                .child("profileImages")
                .child(uid+".jpeg");
        if (uriProfileImage != null) {
            progressBar.setVisibility(View.VISIBLE);
            profileImageRef.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //progressBar.setVisibility(View.GONE);
                            //profileImageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
                            profileImageRef.getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            //progressBar.setVisibility(View.GONE);
                                            profileImageUrl = uri.toString();
                                            //Toast.makeText(getApplicationContext(), "Image Upload Successful", Toast.LENGTH_SHORT).show();
                                            saveUserInfo(uri);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

  /*  private void saveUserInfo2() {
        FirebaseStorage storage= FirebaseStorage.getInstance();
        StorageReference imageRef = storage.getReference().child("images").child("profileImages").child("123.jpg");
        //getting bytes
        imageRef.getBytes(1024*1024)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        imgUser.setImageBitmap(bitmap);
                    }
                });
        //getting download Url
        imageRef.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d(TAG,"Download url is :"+uri.toString());
                        Toast.makeText(getApplicationContext(),"up succ"+uri.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
    }*/

  /*  private void saveUserInfoUploadImg() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference reference = storage.getReference();
//        StorageReference picRef = reference.child("pic.jpg");
        // StorageReference picRef = storage.getReference().child("pictures").child("img.jpg");
        StorageReference picRef = storage.getReference().child("images").child("profileImages").child("123.jpg");
        Bitmap picBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.splash);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        picBitmap.compress(Bitmap.CompressFormat.JPEG,20,baos);
        picRef.putBytes(baos.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"up succ",Toast.LENGTH_SHORT).show();
                    }
                });
    }*/

    private void saveUserInfo(Uri uri) {

//        String displayName= editName.getText().toString();
//        if(displayName.isEmpty()){
//            editName.setError("Name required");
//            editName.requestFocus();
//            return;
//        }

        FirebaseUser user = mAuth.getCurrentUser();

        if (user!=null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                   // .setDisplayName(displayName)
                 //   .setPhotoUri(Uri.parse(profileImageUrl))
                    .setPhotoUri(uri)
                    .build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful())
                    {
                        Toast.makeText(ProfileActivity.this,"Profile updated",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ProfileActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}