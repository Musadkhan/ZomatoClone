package com.example.musadkhan.zomatoclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;


public class User_Profile extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    Button logoutBtn;
    TextView nameTxt,emailTxt;
    ImageView photoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logoutBtn = findViewById(R.id.logoutBtn);
        photoImg = findViewById(R.id.userPhoto);
        nameTxt = findViewById(R.id.name);
        emailTxt = findViewById(R.id.emailId);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();

            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            nameTxt.setText(personName);
            emailTxt.setText(personEmail);
           // Glide.with(this).load(personPhoto).into(photoImg);

            Picasso.get().load(personPhoto).into(photoImg);
        }


    }

    private void logOut()
    {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(User_Profile.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


    }
}