package com.cs407.morningrecovery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingActivity extends AppCompatActivity {

    static final String PREF_NAME = "MyPrefs";
    static final String KEY_QUIZ_ENABLED = "quiz_enabled";
    static final String KEY_QUOTE_ENABLED = "quote_enabled";
    static final String KEY_DAY_NIGHT_MODE = "day_night_mode";

    private boolean isQuizEnabled;
    private boolean isQuoteEnabled;
    private boolean isNightModeEnabled;
    private boolean isChangesMade = false;
    private boolean isNightModeChanged = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button cancelButton = findViewById(R.id.cancel_btn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChangesMade) {
                    showDiscardChangesDialog();
                } else {
                    // No changes made, go back to MainActivity
                    navigateToMainActivity();
                }
            }
        });

        Button saveButton = findViewById(R.id.save_btn);

        // Enable or disable quiz class
        SwitchMaterial quizSwitch = findViewById(R.id.quiz);

        // Enable or disable quote class
        SwitchMaterial quoteSwitch = findViewById(R.id.quote);

        // Initialize night mode switch
        SwitchMaterial switchBtn = findViewById(R.id.nightMode);

        // Load saved switch states from SharedPreferences
        loadSwitchStates();

        quizSwitch.setChecked(isQuizEnabled);
        quoteSwitch.setChecked(isQuoteEnabled);
        switchBtn.setChecked(isNightModeEnabled);

        quizSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isQuizEnabled = isChecked;
                isChangesMade = true;
                if (isQuizEnabled) {
                    Toast.makeText(SettingActivity.this, "Quiz Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "Quiz Disabled", Toast.LENGTH_SHORT).show();
                }

            }
        });

        quoteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isQuoteEnabled = isChecked;
                isChangesMade = true;
                if (isQuoteEnabled) {
                    Toast.makeText(SettingActivity.this, "Quote Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "Quote Disabled", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Switch theme mode per user wishes
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Checking if the switch is turned on
                isNightModeEnabled = isChecked;
                isNightModeChanged = true;
                isChangesMade = true;
                // Set night mode based on the loaded state
                if (isNightModeEnabled) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


        // Add onClickListener to the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save switch states to SharedPreferences
                saveSwitchStates();

                // Display a toast message
                Toast.makeText(SettingActivity.this, "Settings saved!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveSwitchStates() {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_QUIZ_ENABLED, isQuizEnabled);
        editor.putBoolean(KEY_QUOTE_ENABLED, isQuoteEnabled);
        editor.putBoolean(KEY_DAY_NIGHT_MODE, isNightModeEnabled);
        editor.apply();
    }

    private void loadSwitchStates() {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        isQuizEnabled = preferences.getBoolean(KEY_QUIZ_ENABLED, true); // Default to true if not found
        isQuoteEnabled = preferences.getBoolean(KEY_QUOTE_ENABLED, true); // Default to true if not found
        isNightModeEnabled = preferences.getBoolean(KEY_DAY_NIGHT_MODE, false); // Default to true if not found
    }

    private void showDiscardChangesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Discard changes?");
        builder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User confirmed to discard changes, go back to MainActivity
                if (isNightModeChanged) {
                    // Reset night mode to the previous state
                    setNightMode(!isNightModeEnabled);
                }
                navigateToMainActivity();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User chose to cancel the discard operation, do nothing
            }
        });
        builder.show();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setNightMode(boolean isEnabled) {
        isNightModeEnabled = isEnabled;
        isNightModeChanged = false;
        // Set night mode based on the loaded state
        if (isNightModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
