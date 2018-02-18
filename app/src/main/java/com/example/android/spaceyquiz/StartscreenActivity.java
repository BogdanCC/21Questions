package com.example.android.spaceyquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StartscreenActivity extends AppCompatActivity {
    // Global variables we need
    Typeface robotoLight;
    EditText name;
    EditText nameAnswer;
    TextView startscreenQuestion;
    TextView nextText;
    LinearLayout next;
    LinearLayout checkboxesContainer;
    CheckBox c1;
    CheckBox c2;
    CheckBox c3;
    public int pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make activity fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_startscreen);

        // Initialise variables when activity is created
        robotoLight = Typeface.createFromAsset(getAssets(), "fonts/roboto_light.ttf");
        next = findViewById(R.id.next);
        nextText = findViewById(R.id.next_text);
        name = findViewById(R.id.player_name);
        nameAnswer = findViewById(R.id.player_name_answer);
        startscreenQuestion = findViewById(R.id.startscreen_question);
        checkboxesContainer = findViewById(R.id.checkboxes_container);
        c1 = findViewById(R.id.checkbox1);
        c2 = findViewById(R.id.checkbox2);
        c3 = findViewById(R.id.checkbox3);
        c1.setTypeface(robotoLight);
        c2.setTypeface(robotoLight);
        c3.setTypeface(robotoLight);
        pageNumber = 0;

        // Set a click listener on the next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });
    }

    /** Method for the next button */
    public void nextQuestion() {
        Toast t;
        if(pageNumber == 0) {
            pageNumber += 1;
            startscreenQuestion.setText(R.string.name_please);
            name.setVisibility(View.VISIBLE);
            nextText.setText(R.string.next);
        } else if(pageNumber == 1) {
            if(name.getText().toString().equals("")) {
                t = Toast.makeText(this, R.string.no_name_toast, Toast.LENGTH_LONG);
                t.show();
            }
            else {
                pageNumber += 1;
                startscreenQuestion.setText(R.string.startscreen_question);
                name.setVisibility(View.GONE);
                checkboxesContainer.setVisibility(View.VISIBLE);
            }
        } else if(pageNumber == 2){
            if(c1.isChecked() && c3.isChecked() && !c2.isChecked()) {
                pageNumber += 1;
                checkboxesContainer.setVisibility(View.GONE);
                startscreenQuestion.setText(R.string.whats_your_name);
                nameAnswer.setVisibility(View.VISIBLE);
            } else{
                t = Toast.makeText(this, R.string.wrong_answers_toast, Toast.LENGTH_LONG);
                t.show();
            }
        } else if(pageNumber == 3) {
            if(nameAnswer.getText().toString().trim().equals(name.getText().toString().trim())) {
                pageNumber += 1;
                nextText.setText(R.string.start_main_quiz);
                String playerName = name.getText().toString();
                nameAnswer.setVisibility(View.GONE);
                startscreenQuestion.setText(getString(R.string.congratulations, playerName));
            } else {
                t = Toast.makeText(this, getString(R.string.not_your_name_toast) + " " + name.getText().toString().trim(), Toast.LENGTH_LONG);
                t.show();
            }
        }
        else if(pageNumber == 4) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
