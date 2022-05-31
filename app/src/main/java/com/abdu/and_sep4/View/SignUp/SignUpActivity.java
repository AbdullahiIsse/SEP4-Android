package com.abdu.and_sep4.View.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.User;
import com.abdu.and_sep4.View.Login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private AppCompatEditText etName;
    private AppCompatEditText et_Email;
    private AppCompatEditText et_Password;
    private Button signup;
    private Button back;
    private SignUpActivityViewModel signUpActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpActivityViewModel = new ViewModelProvider(this).get(SignUpActivityViewModel.class);

        firebaseAuth = FirebaseAuth.getInstance();
        etName = findViewById(R.id.et_full_name_sign_up);
        et_Email = findViewById(R.id.et_email_sign_up);
        et_Password = findViewById(R.id.et_password_sign_up);
        signup = findViewById(R.id.btn_sign_up);
        back = findViewById(R.id.back);


        back.setOnClickListener(this::Back);
        signup.setOnClickListener(this::Register);


    }


    public void Back(View v) {
        startActivity(new Intent(this, LoginActivity.class));
    }


    private void Register(View view) {
        if (ifNetworkIsAvailable()) {
            String name = etName.getText().toString();
            String email = et_Email.getText().toString();
            String password = et_Password.getText().toString();

            validations(name, email, password);

            firebaseAuthAndSave(name, email, password);

            etName.setText("");
            et_Email.setText("");
            et_Password.setText("");
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

    private void firebaseAuthAndSave(String name, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        //    User user = new User(FirebaseAuth.getInstance().getCurrentUser().getUid(), name, email);
                            User user1 = new User(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            signUpActivityViewModel.addUser(user1);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed to register user", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void validations(String name, String email, String password) {
        if (name.isEmpty()) {
            etName.setError("Name can not be empty");
            etName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            et_Email.setError("Email can not be Empty");
            et_Email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_Email.setError("Email must be valid");
            et_Email.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            et_Password.setError("Password can not be empty");
            et_Password.requestFocus();
            return;
        }

        if (password.length() < 8) {
            et_Password.setError("Password length must be 8 or more");
            et_Password.requestFocus();
            return;
        }
    }


}