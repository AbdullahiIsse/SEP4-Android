package com.abdu.and_sep4.View.Setting;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;
import com.abdu.and_sep4.View.Login.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends PreferenceFragmentCompat {

    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient signInClient;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        EditTextPreference usernamePreference = findPreference("username");

        if (firebaseUser != null) {
            usernamePreference.setText(firebaseUser.getEmail());

        }

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        signInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions);


        Preference signOutPreference = findPreference("signout");

        signOutPreference.setOnPreferenceClickListener(this::signOut);


    }

    private boolean signOut(Preference preference) {

        firebaseAuth.signOut();
        signInClient.signOut();
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
        return true;
    }








}