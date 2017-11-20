package com.example.android.spaceyquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SpaceFragment extends Fragment {
    /**
     * Creating the needed variables
     */
    public int currentQuestion = 0; // integer to store the current question
    public int totalQuestions = 7; // total number of questions
    public int quizScore = 0; // int to store the correct answers
    public int wrongAnswers; // wrong answers will be total answers - right answers
    public long timeStart; // variable to store the time when the quiz started
    public long timeEnd; // variable to store the time when the quiz ends (after final submit)
    public int imageCount = 0; // setting imageCount to 0, see the changeImageListener method

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_space, container, false);
        // start the timer when view is created
        timeStart = System.currentTimeMillis();
        TextView questionNumber = rootView.findViewById(R.id.current_question);
        // reset these variables when view(fragment) is created
        currentQuestion = 0;
        quizScore = 0;
        imageCount = 0;
        questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
        // Get Questions and Answers to set the text for Space
        TextView questionTextView = rootView.findViewById(R.id.question_text_view);
        RadioButton checkbox1 = rootView.findViewById(R.id.answer1);
        RadioButton checkbox2 = rootView.findViewById(R.id.answer2);
        RadioButton checkbox3 = rootView.findViewById(R.id.answer3);
        RadioButton checkbox4 = rootView.findViewById(R.id.answer4);
        questionTextView.setText(R.string.question_one);
        checkbox1.setText(R.string.trick_answer1_1);
        checkbox2.setText(R.string.trick_answer2_1);
        checkbox3.setText(R.string.correct_answer3_1);
        checkbox4.setText(R.string.trick_answer4_1);
        // change image listener
        ImageView changeImage = rootView.findViewById(R.id.change_image);
        changeImage.setOnClickListener(changeImageListener);
        // show hint listener
        ImageView showHint = rootView.findViewById(R.id.show_hint);
        showHint.setOnClickListener(showHintListener);
        // submit answer listener
        Button submitButton = rootView.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(submitAnswer);
        // reset button listener
        Button resetButton = rootView.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(resetQuiz);
        return rootView;
    }

    /**
     * Fun little method to change between 3 backgrounds
     * When we first click : imageCount is 0 as we initialised it
     * Once we clicked once it changes the background as stated in the first if statement that is true
     * imageCount becomes 1. Second click, first else if block is true, changes background;
     * imageCount becomes 2. Thirs click, second else if block is true, changes background to the initial background set in the xml
     * imageCount becomes 0. So it starts all over again at the next click.
     */
    private View.OnClickListener changeImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageView image = getActivity().findViewById(R.id.changeable_image);
            if (imageCount == 0) {
                image.setImageResource(R.drawable.background22);
                imageCount++;
            } else if (imageCount == 1) {
                image.setImageResource(R.drawable.background3);
                imageCount++;
            } else if (imageCount == 2) {
                image.setImageResource(R.drawable.descarcare);
                imageCount = 0; // if this is the 3rd image, make imageCount 0 to start over frm the first
            }
        }
    };
    /**
     * Method for the hint
     * Check what current question it is when we click on the hint image
     * and display the appropriate hint with a toast
     */
    private View.OnClickListener showHintListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Creating the toast messages
            Toast hint1 = Toast.makeText(getContext(), getString(R.string.question_one_hint), Toast.LENGTH_LONG);
            Toast hint2 = Toast.makeText(getContext(), getString(R.string.question_two_hint), Toast.LENGTH_LONG);
            Toast hint3 = Toast.makeText(getContext(), getString(R.string.question_three_hint), Toast.LENGTH_LONG);
            Toast hint4 = Toast.makeText(getContext(), getString(R.string.question_four_hint), Toast.LENGTH_LONG);
            Toast hint5 = Toast.makeText(getContext(), getString(R.string.question_five_hint), Toast.LENGTH_LONG);
            Toast hint6 = Toast.makeText(getContext(), getString(R.string.question_six_hint), Toast.LENGTH_LONG);
            Toast hint7 = Toast.makeText(getContext(), getString(R.string.question_seven_hint), Toast.LENGTH_LONG);
            // Showing the toasts at the corresponding questions
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
    };
    /**
     * Our huge submit answer method
     */
    private View.OnClickListener submitAnswer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /** Getting radio buttons, text views and other views to manipulate them */
            RadioButton checkbox1 = getActivity().findViewById(R.id.answer1);
            RadioButton checkbox2 = getActivity().findViewById(R.id.answer2);
            RadioButton checkbox3 = getActivity().findViewById(R.id.answer3);
            RadioButton checkbox4 = getActivity().findViewById(R.id.answer4);
            TextView finalCorrectAnswers = getActivity().findViewById(R.id.final_correct_answers);
            TextView finalWrongAnswers = getActivity().findViewById(R.id.final_wrong_answers);
            TextView finalTimeElapsed = getActivity().findViewById(R.id.final_time_elapsed);
            TextView question = getActivity().findViewById(R.id.question_text_view);
            TextView questionNumber = getActivity().findViewById(R.id.current_question);
            View finishedQuiz = getActivity().findViewById(R.id.finished_quiz);
            /** Handling an error in case user doesn't check any answer OR all questions were answered */
            Toast error = Toast.makeText(getContext(), getString(R.string.no_answer_checked), Toast.LENGTH_LONG);
            Toast errorFinalQuestion = Toast.makeText(getContext(), getString(R.string.final_question), Toast.LENGTH_LONG);
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
                // Get the next question and answers to set them after we're done with this current question
                String nextAnswerOneText = getResources().getString(R.string.correct_answer1_2);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_2);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_2);
                String nextAnswerFourText = getResources().getString(R.string.trick_answer4_2);
                String nextQuestion = getString(R.string.question_two);
                // Get the correct answer for the current question to compare it with the selected radio button
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer3_1);
                // Comparing the selected radio button (whichever the user might choose) with the correct answer
                // Add 1 to the score if they match
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
                // Now that we are done with the current question and answers set the next set of Q&A
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                // We are done with this question, add 1 so we can go to the next one
                currentQuestion++;
                // Also set the bottom text '1 0f 7' to '2 of 7'
                // We pass in the currentQuestion + 1 and totalQuestions for the xml string (see strings.xml)
                questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
            } else if (currentQuestion == 1) {
                // We repeat the same process as above :
                // 1. GET next set of answers and the next question
                // 2. Compare the correct answer to the selected answer - add 1 to the score if they match
                // 3. SET the next answers and next question to the TextViews
                // 4. Add 1 to currentQuestion so we can move to the next step
                String nextAnswerOneText = getResources().getString(R.string.trick_answer1_3);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_3);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_3);
                String nextAnswerFourText = getResources().getString(R.string.correct_answer4_3);
                String nextQuestion = getString(R.string.question_three);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer1_2);
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
                String nextAnswerOneText = getResources().getString(R.string.trick_answer1_4);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_4);
                String nextAnswerThreeText = getResources().getString(R.string.correct_answer3_4);
                String nextAnswerFourText = getResources().getString(R.string.trick_answer4_4);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_3);
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
                String nextQuestion = getString(R.string.question_four);
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
            } else if (currentQuestion == 3) {
                String nextAnswerOneText = getResources().getString(R.string.trick_answer1_5);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_5);
                String nextAnswerThreeText = getResources().getString(R.string.correct_answer3_5);
                String nextAnswerFourText = getResources().getString(R.string.trick_answer4_5);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer3_4);
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
                String nextQuestion = getString(R.string.question_five);
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
            } else if (currentQuestion == 4) {
                String nextAnswerOneText = getResources().getString(R.string.correct_answer1_6);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_6);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_6);
                String nextAnswerFourText = getResources().getString(R.string.trick_answer4_6);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer3_5);
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
                String nextQuestion = getString(R.string.question_six);
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
            } else if (currentQuestion == 5) {
                String nextAnswerOneText = getResources().getString(R.string.trick_answer1_7);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_7);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_7);
                String nextAnswerFourText = getResources().getString(R.string.correct_answer4_7);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer1_6);
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
                String nextQuestion = getString(R.string.question_seven);
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
            } else if (currentQuestion == 6) {
                // This is the final question so we don't have any more questions and answers to get
                // Now we compare the selected answer with the correct answer as we did before
                // And we display the finished screen with the results
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_7);
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
                currentQuestion++; // now currentQuestion is = to totalQuestions(7) so the toast error will show up if we click submit
                wrongAnswers = totalQuestions - quizScore; // correct answers : quizScore; wrong answers : totalQuestions - quizScore
                timeEnd = System.currentTimeMillis(); // get the current time
                long timeDelta = timeEnd - timeStart; // find out time elapsed (timeStart - when quiz started, timeEnd - when quiz ended)
                double elapsedSeconds = timeDelta / 1000.0; // this is to show it in seconds
                // Now we destroy the answers view so we can show the final view that was set to View.GONE this whole time
                checkbox1.setVisibility(View.GONE);
                checkbox2.setVisibility(View.GONE);
                checkbox3.setVisibility(View.GONE);
                checkbox4.setVisibility(View.GONE);
                // Show the results screen and set the correct text to each TextView
                finishedQuiz.setVisibility(View.VISIBLE);
                question.setText(getString(R.string.all_questions_done));
                finalCorrectAnswers.setText("" + quizScore);
                finalWrongAnswers.setText("" + wrongAnswers);
                finalTimeElapsed.setText("" + elapsedSeconds + " sec");
            }
        }
    };
    /**
     * Reset everything back to normal
     */
    private View.OnClickListener resetQuiz = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /** Reset the timer only at the end or before the second question to limit cheating */
            if (currentQuestion <= 1 || currentQuestion == 7) {
                timeStart = System.currentTimeMillis();
            }
            currentQuestion = 0;
            quizScore = 0;
            RadioButton checkbox1 = getActivity().findViewById(R.id.answer1);
            RadioButton checkbox2 = getActivity().findViewById(R.id.answer2);
            RadioButton checkbox3 = getActivity().findViewById(R.id.answer3);
            RadioButton checkbox4 = getActivity().findViewById(R.id.answer4);
            View finishedQuiz = getActivity().findViewById(R.id.finished_quiz);
            TextView question = getActivity().findViewById(R.id.question_text_view);
            TextView questionNumber = getActivity().findViewById(R.id.current_question);
            finishedQuiz.setVisibility(View.GONE);
            checkbox1.setVisibility(View.VISIBLE);
            checkbox2.setVisibility(View.VISIBLE);
            checkbox3.setVisibility(View.VISIBLE);
            checkbox4.setVisibility(View.VISIBLE);
            questionNumber.setVisibility(View.VISIBLE);
            checkbox1.setText(getResources().getString(R.string.trick_answer1_1));
            checkbox2.setText(getResources().getString(R.string.trick_answer2_1));
            checkbox3.setText(getResources().getString(R.string.correct_answer3_1));
            checkbox4.setText(getResources().getString(R.string.trick_answer4_1));
            questionNumber.setText(getString(R.string.current_question, currentQuestion + 1, totalQuestions));
            question.setText(getResources().getString(R.string.question_one));
        }
    };
}
