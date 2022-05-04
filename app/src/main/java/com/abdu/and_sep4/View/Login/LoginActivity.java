package com.abdu.and_sep4.View.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.Shared.User;
import com.abdu.and_sep4.View.Login.LoginActivityViewModel;
import com.abdu.and_sep4.View.Main.MainActivity;

public class LoginActivity extends AppCompatActivity {


    private AppCompatEditText username;
    private AppCompatEditText password;
    private TextView error;
    private Button loginBtn;
    private LoginActivityViewModel loginActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        error = findViewById(R.id.tv_error);
        loginBtn = findViewById(R.id.btn_login);

        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);


        loginBtn.setOnClickListener(this::onLogin);
    }



    public void onLogin(View v) {

        String loginUsername = username.getText().toString();
        String loginPassword = password.getText().toString();



        if (loginUsername.isEmpty()){

            error.setText("username can not be empty");
            return;
        }

        if (loginPassword.isEmpty()){
            error.setText("password can not be empty");
            return;
        }

        LiveData<User> user = loginActivityViewModel.validateUser(loginUsername, loginPassword);



        user.observe(this,userResponse -> {

          if (userResponse != null){
              SaveInfo.getInstance().setUser(userResponse);
              Log.e("test", String.valueOf(userResponse.getId()));
              Intent intent = new Intent(this, MainActivity.class);
              startActivity(intent);
              clearFields();

          } else {

              error.setText("User not found");
          }


        });







    }


    public void clearFields() {
        username.setText("");
        password.setText("");
        error.setText("");
    }












}