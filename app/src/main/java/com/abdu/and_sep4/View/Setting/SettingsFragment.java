package com.abdu.and_sep4.View.Setting;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.abdu.and_sep4.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);


    }
}