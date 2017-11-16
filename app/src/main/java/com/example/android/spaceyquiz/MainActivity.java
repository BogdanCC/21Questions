package com.example.android.spaceyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // find views and create intents for activities
        TextView toSpaceQuiz = findViewById(R.id.to_space_quiz);
        toSpaceQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSpaceQuizIntent = new Intent(MainActivity.this, SpaceActivity.class);
                startActivity(toSpaceQuizIntent);
            }
        });
        TextView toOtherQuiz = findViewById(R.id.to_other_quiz);
        toOtherQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toOtherQuizIntent = new Intent(MainActivity.this, GotActivity.class);
                startActivity(toOtherQuizIntent);
            }
        });
        TextView toOtherQuiz2 = findViewById(R.id.to_other_quiz2);
        toOtherQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toOtherQuizIntent2 = new Intent(MainActivity.this, QuantumActivity.class);
                startActivity(toOtherQuizIntent2);
            }
        });
    }



}
