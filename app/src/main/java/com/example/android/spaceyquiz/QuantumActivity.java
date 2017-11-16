package com.example.android.spaceyquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bogda on 11/16/2017.
 */

public class QuantumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quantum);
        // start the timer when application starts
        timeStart = System.currentTimeMillis();
        TextView questionNumber = findViewById(R.id.current_question);
        questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        // Array list with question and answers objects
        // Get Questions and Answers to set the text for GOT
        TextView questionTextView = findViewById(R.id.question_text_view);
        RadioButton checkbox1 = findViewById(R.id.answer1);
        RadioButton checkbox2 = findViewById(R.id.answer2);
        RadioButton checkbox3 = findViewById(R.id.answer3);
        RadioButton checkbox4 = findViewById(R.id.answer4);
        questionTextView.setText(R.string.question_one_quantum);
        checkbox1.setText(R.string.trick_answer1_1_quantum);
        checkbox2.setText(R.string.trick_answer2_1_quantum);
        checkbox3.setText(R.string.trick_answer3_1_quantum);
        checkbox4.setText(R.string.correct_answer4_1_quantum);
    }


    /**
     * Creating the needed variables
     */
    public int currentQuestion = 0; // integer to store the current question
    public int totalQuestions = 7; // total number of questions
    public int quizScore = 0; // int to store the correct answers
    public int wrongAnswers; // wrong answers will be total answers - right answers
    public long timeStart;
    public long timeEnd;
    public int imageCount = 0;

    /**
     * Fun little method to change between 3 backgrounds
     */
    public void changeImage(View view) {
        ImageView image = findViewById(R.id.changeable_image);
        if (imageCount == 0) {
            image.setImageResource(R.drawable.quantum2);
            imageCount++;
        } else if (imageCount == 1) {
            image.setImageResource(R.drawable.quantum3);
            imageCount++;
        } else if (imageCount == 2) {
            image.setImageResource(R.drawable.quantum1);
            imageCount = 0; // if this is the 3rd image, make imageCount 0 to start over frm the first
        }
    }

    /**
     * Our huge submit answer method
     */
    public void submitAnswer(View view) {
        /** Getting radio buttons, text views and other views to manipulate them */
        RadioButton checkbox1 = findViewById(R.id.answer1);
        RadioButton checkbox2 = findViewById(R.id.answer2);
        RadioButton checkbox3 = findViewById(R.id.answer3);
        RadioButton checkbox4 = findViewById(R.id.answer4);
        TextView finalCorrectAnswers = findViewById(R.id.final_correct_answers);
        TextView finalWrongAnswers = findViewById(R.id.final_wrong_answers);
        TextView finalTimeElapsed = findViewById(R.id.final_time_elapsed);
        TextView question = findViewById(R.id.question_text_view);
        TextView questionNumber = findViewById(R.id.current_question);
        View finishedQuiz = findViewById(R.id.finished_quiz);

        /** Handling an error in case user doesn't check any answer OR all questions were answered */
        Toast error = Toast.makeText(this, getString(R.string.no_answer_checked), Toast.LENGTH_LONG);
        Toast errorFinalQuestion = Toast.makeText(this, getString(R.string.final_question), Toast.LENGTH_LONG);
        if (!checkbox1.isChecked() && !checkbox2.isChecked() && !checkbox3.isChecked() && !checkbox4.isChecked()) {
            error.show();
            return; // break out of method if no answers selected
        }

        if (currentQuestion == totalQuestions) {
            errorFinalQuestion.show();
        }
        /** When we click the submit button we check to see what question this is, starting from 0
         * GET the next question and answers so once we click submit we can SET them in our TextViews
         * Check to see if the selected answer's (isSelected) text string is equal to the current correct answer
         * If it is, add 1 to our score
         */

        if (currentQuestion == 0) {
            String nextAnswerOneText = getResources().getString(R.string.correct_answer1_2_quantum);
            String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_2_quantum);
            String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_2_quantum);
            String nextAnswerFourText = getResources().getString(R.string.trick_answer4_2_quantum);
            String nextQuestion = getString(R.string.question_two_quantum);
            String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_1_quantum);
            if (checkbox1.isChecked()) {
                if (checkbox1.getText().toString() == currentCorrectAnswer) {
                    quizScore++;
                }
            }
            if (checkbox2.isChecked()) {
                if (checkbox2.getText().toString() == currentCorrectAnswer) {
                    quizScore++;
                }
            }
            if (checkbox3.isChecked()) {
                if (checkbox3.getText().toString() == currentCorrectAnswer) {
                    quizScore++;
                }
            }
            if (checkbox4.isChecked()) {
                if (checkbox4.getText().toString() == currentCorrectAnswer) {
                    quizScore++;
                }
            }
            checkbox1.setText(nextAnswerOneText);
            checkbox2.setText(nextAnswerTwoText);
            checkbox3.setText(nextAnswerThreeText);
            checkbox4.setText(nextAnswerFourText);
            question.setText(nextQuestion);
            currentQuestion++;
            questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        } else if (currentQuestion == 1) {
            String nextAnswerOneText = getResources().getString(R.string.trick_answer1_3_quantum);
            String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_3_quantum);
            String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_3_quantum);
            String nextAnswerFourText = getResources().getString(R.string.correct_answer4_3_quantum);
            String nextQuestion = getString(R.string.question_three_quantum);
            String currentCorrectAnswer = getResources().getString(R.string.correct_answer1_2_quantum);
            if (checkbox1.isChecked()) {
                if (checkbox1.getText().toString() == currentCorrectAnswer) {
                    quizScore++;
                }
            }
            if (checkbox2.isChecked()) {
                if (checkbox2.getText().toString() == currentCorrectAnswer) {
                    quizScore++;
                }
            }
            if (checkbox3.isChecked()) {
                if (checkbox3.getText().toString() == currentCorrectAnswer) {
                    quizScore++;
                }
            }
            if (checkbox4.isChecked()) {
                if (checkbox4.getText().toString() == currentCorrectAnswer) {
                    quizScore++;
                }
            }
            checkbox1.setText(nextAnswerOneText);
            checkbox2.setText(nextAnswerTwoText);
            checkbox3.setText(nextAnswerThreeText);
            checkbox4.setText(nextAnswerFourText);
            question.setText(nextQuestion);
            currentQuestion++;
            questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        } else if (currentQuestion == 2) {
            String nextAnswerOneText = getResources().getString(R.string.trick_answer1_4_quantum);
            String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_4_quantum);
            String nextAnswerThreeText = getResources().getString(R.string.correct_answer3_4_quantum);
            String nextAnswerFourText = getResources().getString(R.string.trick_answer4_4_quantum);
            String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_3_quantum);
            if (checkbox1.isChecked()) {
                if (checkbox1.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox2.isChecked()) {
                if (checkbox2.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox3.isChecked()) {
                if (checkbox3.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox4.isChecked()) {
                if (checkbox4.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            String nextQuestion = getString(R.string.question_four_quantum);
            checkbox1.setText(nextAnswerOneText);
            checkbox2.setText(nextAnswerTwoText);
            checkbox3.setText(nextAnswerThreeText);
            checkbox4.setText(nextAnswerFourText);
            question.setText(nextQuestion);
            currentQuestion++;
            questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        } else if (currentQuestion == 3) {
            String nextAnswerOneText = getResources().getString(R.string.trick_answer1_5_quantum);
            String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_5_quantum);
            String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_5_quantum);
            String nextAnswerFourText = getResources().getString(R.string.correct_answer4_5_quantum);
            String currentCorrectAnswer = getResources().getString(R.string.correct_answer3_4_quantum);
            if (checkbox1.isChecked()) {
                if (checkbox1.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox2.isChecked()) {
                if (checkbox2.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox3.isChecked()) {
                if (checkbox3.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox4.isChecked()) {
                if (checkbox4.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            String nextQuestion = getString(R.string.question_five_quantum);
            checkbox1.setText(nextAnswerOneText);
            checkbox2.setText(nextAnswerTwoText);
            checkbox3.setText(nextAnswerThreeText);
            checkbox4.setText(nextAnswerFourText);
            question.setText(nextQuestion);
            currentQuestion++;
            questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        } else if (currentQuestion == 4) {
            String nextAnswerOneText = getResources().getString(R.string.correct_answer1_6_quantum);
            String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_6_quantum);
            String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_6_quantum);
            String nextAnswerFourText = getResources().getString(R.string.trick_answer4_6_quantum);
            String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_5_quantum);
            if (checkbox1.isChecked()) {
                if (checkbox1.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox2.isChecked()) {
                if (checkbox2.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox3.isChecked()) {
                if (checkbox3.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox4.isChecked()) {
                if (checkbox4.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            String nextQuestion = getString(R.string.question_six_quantum);
            checkbox1.setText(nextAnswerOneText);
            checkbox2.setText(nextAnswerTwoText);
            checkbox3.setText(nextAnswerThreeText);
            checkbox4.setText(nextAnswerFourText);
            question.setText(nextQuestion);
            currentQuestion++;
            questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        } else if (currentQuestion == 5) {
            String nextAnswerOneText = getResources().getString(R.string.trick_answer1_7_quantum);
            String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_7_quantum);
            String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_7_quantum);
            String nextAnswerFourText = getResources().getString(R.string.correct_answer4_7_quantum);
            String currentCorrectAnswer = getResources().getString(R.string.correct_answer1_6_quantum);
            if (checkbox1.isChecked()) {
                if (checkbox1.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox2.isChecked()) {
                if (checkbox2.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox3.isChecked()) {
                if (checkbox3.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox4.isChecked()) {
                if (checkbox4.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            String nextQuestion = getString(R.string.question_seven_quantum);
            checkbox1.setText(nextAnswerOneText);
            checkbox2.setText(nextAnswerTwoText);
            checkbox3.setText(nextAnswerThreeText);
            checkbox4.setText(nextAnswerFourText);
            question.setText(nextQuestion);
            currentQuestion++;
            questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        } else if (currentQuestion == 6) {

            String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_7_quantum);
            if (checkbox1.isChecked()) {
                if (checkbox1.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox2.isChecked()) {
                if (checkbox2.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox3.isChecked()) {
                if (checkbox3.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            if (checkbox4.isChecked()) {
                if (checkbox4.getText().toString() == currentCorrectAnswer) {
                    quizScore++;

                }
            }
            currentQuestion++;
            wrongAnswers = totalQuestions - quizScore;
            timeEnd = System.currentTimeMillis();
            long timeDelta = timeEnd - timeStart;
            double elapsedSeconds = timeDelta / 1000.0;
            checkbox1.setVisibility(View.GONE);
            checkbox2.setVisibility(View.GONE);
            checkbox3.setVisibility(View.GONE);
            checkbox4.setVisibility(View.GONE);
            finishedQuiz.setVisibility(View.VISIBLE);
            question.setText(getString(R.string.all_questions_done));
            finalCorrectAnswers.setText("" + quizScore);
            finalWrongAnswers.setText("" + wrongAnswers);
            finalTimeElapsed.setText("" + elapsedSeconds + " sec");
        }
    }

    /**
     * Reset everything back to normal
     */
    public void resetQuiz(View view) {
        /** Reset the timer only at the end or before the second question to limit cheating */
        if (currentQuestion <= 1 || currentQuestion == 7) {
            timeStart = System.currentTimeMillis();
        }
        currentQuestion = 0;
        quizScore = 0;
        RadioButton checkbox1 = findViewById(R.id.answer1);
        RadioButton checkbox2 = findViewById(R.id.answer2);
        RadioButton checkbox3 = findViewById(R.id.answer3);
        RadioButton checkbox4 = findViewById(R.id.answer4);
        View finishedQuiz = findViewById(R.id.finished_quiz);
        TextView question = findViewById(R.id.question_text_view);
        TextView questionNumber = findViewById(R.id.current_question);
        finishedQuiz.setVisibility(View.GONE);
        checkbox1.setVisibility(View.VISIBLE);
        checkbox2.setVisibility(View.VISIBLE);
        checkbox3.setVisibility(View.VISIBLE);
        checkbox4.setVisibility(View.VISIBLE);
        questionNumber.setVisibility(View.VISIBLE);
        checkbox1.setText(getResources().getString(R.string.trick_answer1_1_quantum));
        checkbox2.setText(getResources().getString(R.string.trick_answer2_1_quantum));
        checkbox3.setText(getResources().getString(R.string.trick_answer3_1_quantum));
        checkbox4.setText(getResources().getString(R.string.correct_answer4_1_quantum));
        questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        question.setText(getResources().getString(R.string.question_one_quantum));
    }

    /**
     * Method for the hint
     * Check what current question it is when we click on the hint image
     * and display the appropriate hint with a toast
     */
    public void showHint(View view) {
        Toast hint1 = Toast.makeText(this, getString(R.string.question_one_hint_quantum), Toast.LENGTH_LONG);
        Toast hint2 = Toast.makeText(this, getString(R.string.question_two_hint_quantum), Toast.LENGTH_LONG);
        Toast hint3 = Toast.makeText(this, getString(R.string.question_three_hint_quantum), Toast.LENGTH_LONG);
        Toast hint4 = Toast.makeText(this, getString(R.string.question_four_hint_quantum), Toast.LENGTH_LONG);
        Toast hint5 = Toast.makeText(this, getString(R.string.question_five_hint_quantum), Toast.LENGTH_LONG);
        Toast hint6 = Toast.makeText(this, getString(R.string.question_six_hint_quantum), Toast.LENGTH_LONG);
        Toast hint7 = Toast.makeText(this, getString(R.string.question_seven_hint_quantum), Toast.LENGTH_LONG);
        if (currentQuestion == 0) {
            hint1.show();
        } else if (currentQuestion == 1) {
            hint2.show();
        } else if (currentQuestion == 2) {
            hint3.show();
        } else if (currentQuestion == 3) {
            hint4.show();
        } else if (currentQuestion == 4) {
            hint5.show();
        } else if (currentQuestion == 5) {
            hint6.show();
        } else {
            hint7.show();
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.answer1:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.answer2:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.answer3:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.answer4:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }
}
