package com.abdu.and_sep4.View.Setting;

import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.abdu.and_sep4.R;
import com.abdu.and_sep4.Shared.SaveInfo;

public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);


        EditTextPreference usernamePreference = findPreference("username");

        if (SaveInfo.getInstance().getUser() != null) {
            usernamePreference.setText(SaveInfo.getInstance().getUser().getUsername());

        }


    }
}