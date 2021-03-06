package com.abdu.and_sep4.View.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.User;
import com.abdu.and_sep4.View.Login.LoginActivityViewModel;
import com.abdu.and_sep4.View.Main.MainActivity;
import com.abdu.and_sep4.View.SignUp.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {


    private SignInButton signInButton;
    private GoogleSignInClient signInClient;
    private static final int RC_SIGN_IN = 9999;
    private FirebaseAuth firebaseAuth;
    private Button signUp;
    private Button btn_login;
    private AppCompatEditText et_email;
    private AppCompatEditText et_password;
    private LoginActivityViewModel loginActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        signInButton = findViewById(R.id.google_sign_in);
        signUp = findViewById(R.id.btn_sign_up);
        btn_login = findViewById(R.id.btn_login);
        et_email = findViewById(R.id.et_username_sign_in);
        et_password = findViewById(R.id.et_password_sign_in);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        signInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        firebaseAuth = FirebaseAuth.getInstance();
        signInButton.setOnClickListener(this::signInWithGoogle);

        signUp.setOnClickListener(this::signUp);

        btn_login.setOnClickListener(this::signIn);
    }



    private void signUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void signInWithGoogle(View view) {
        if (ifNetworkIsAvailable()) {
            Intent signInIntent = signInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        } else {
            Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_LONG).show();
        }
    }

    public boolean ifNetworkIsAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null) {
            if (info.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount googleSignInAccount = accountTask.getResult(ApiException.class);
                handleSignInResult(googleSignInAccount);


            } catch (Exception e) {
                Log.w("LoginActivity", "Google sign in failed", e);

            }
        }


    }

    private void handleSignInResult(GoogleSignInAccount googleSignInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                loginActivityViewModel.addUser(new User(authResult.getUser().getUid()));
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void signIn(View view) {
        if (ifNetworkIsAvailable()) {
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();

            if (email.isEmpty()){
                et_email.setError("email can not be empty");
                et_email.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email.setError("Email must be valid");
                et_email.requestFocus();
                return;
            }

            if (password.isEmpty()){
                et_password.setError("password can not be empty");
                et_password.requestFocus();
                return;
            }


            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user.isEmailVerified()){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();

                        }else{
                            user.sendEmailVerification();
                            Toast.makeText(LoginActivity.this,"Check email to verify account",Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(LoginActivity.this,"Failed to Login",Toast.LENGTH_LONG).show();
                    }
                }
            });


            et_email.setText("");
            et_password.setText("");
        } else {
            Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_LONG).show();
        }



    }




}