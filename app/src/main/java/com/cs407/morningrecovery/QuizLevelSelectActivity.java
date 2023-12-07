package com.cs407.morningrecovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizLevelSelectActivity extends AppCompatActivity {
    // variable for tracking quiz type/level
    // it is used in QuizActivity
    public static int level;

    private void moveToMainPage() {
        Intent intent = new Intent(QuizLevelSelectActivity.this, MainActivity.class);
        // You can pass any necessary data to the QuoteActivity using intent.putExtra if needed
        startActivity(intent);
        // Finish the current activity if you don't want the user to come back to the quiz after seeing the quote
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlevelselect);

        // CUSTOM 버튼에 대한 참조를 가져옵니다.
        Button customButton = findViewById(R.id.custombutton);
        Button cancelButton = findViewById(R.id.quizselectCancelbutton);

        // CUSTOM 버튼에 클릭 리스너를 설정합니다.
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent를 사용하여 CustomQuizActivity를 시작합니다.
                Intent intent = new Intent(QuizLevelSelectActivity.this, CustomQuizActivity.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 4;
                Intent intent = new Intent(QuizLevelSelectActivity.this, SetAlarmActivity.class);
                startActivity(intent);
            }
        });


        // user select the type of quiz
        Button easyButton = findViewById(R.id.easybutton);
        Button mediumButton = findViewById(R.id.mediumbutton);
        Button hardButton = findViewById(R.id.hardbutton);

        // actions when each button get clicked
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the level to 1 when Easy button is clicked
                level = 1;
                Toast.makeText(QuizLevelSelectActivity.this, "You select the easy one!", Toast.LENGTH_LONG).show();
                moveToMainPage();
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the level to 2 when Medium button is clicked
                level = 2;
                Toast.makeText(QuizLevelSelectActivity.this, "You select the medium one!", Toast.LENGTH_LONG).show();
                moveToMainPage();
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the level to 3 when Hard button is clicked
                level = 3;
                Toast.makeText(QuizLevelSelectActivity.this, "You select the hard one!", Toast.LENGTH_LONG).show();
                moveToMainPage();
            }
        });


    }
}
