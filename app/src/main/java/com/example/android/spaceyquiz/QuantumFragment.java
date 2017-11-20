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

/**
 * Created by bogda on 11/19/2017.
 */

public class QuantumFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quantum, container, false);
        // start the timer when view is created
        timeStart = System.currentTimeMillis();
        // Get Questions and Answers to set the text for Quantum
        TextView questionTextView = rootView.findViewById(R.id.question_text_view2);
        RadioButton checkbox1 = rootView.findViewById(R.id.answer12);
        RadioButton checkbox2 = rootView.findViewById(R.id.answer22);
        RadioButton checkbox3 = rootView.findViewById(R.id.answer32);
        RadioButton checkbox4 = rootView.findViewById(R.id.answer42);
        questionTextView.setText(R.string.question_one_quantum);
        checkbox1.setText(R.string.trick_answer1_1_quantum);
        checkbox2.setText(R.string.trick_answer2_1_quantum);
        checkbox3.setText(R.string.trick_answer3_1_quantum);
        checkbox4.setText(R.string.correct_answer4_1_quantum);
        TextView questionNumber = rootView.findViewById(R.id.current_question2);
        // reset these variables when the view(fragment) is created
        currentQuestion2 = 0;
        quizScore2 = 0;
        imageCount2 = 0;
        questionNumber.setText(getString(R.string.current_question, currentQuestion2 + 1, totalQuestions));
        // change image listener
        ImageView changeImage = rootView.findViewById(R.id.change_image2);
        changeImage.setOnClickListener(changeImageListener);
        // show hint listener
        ImageView showHint = rootView.findViewById(R.id.show_hint2);
        showHint.setOnClickListener(showHintListener);
        // submit answer listener
        Button submitButton = rootView.findViewById(R.id.submit_button2);
        submitButton.setOnClickListener(submitAnswerListener);
        // reset button listener
        Button resetButton = rootView.findViewById(R.id.reset_button2);
        resetButton.setOnClickListener(resetQuizListener);
        return rootView;
    }

    /**
     * Creating the needed variables
     */
    public int currentQuestion2 = 0; // integer to store the current question
    public int totalQuestions = 7; // total number of questions
    public int quizScore2 = 0; // int to store the correct answers
    public int wrongAnswers; // wrong answers will be total answers - right answers
    public long timeStart; // variable to store the time when the quiz started
    public long timeEnd; // variable to store the time when the quiz ends (after final submit)// variable to store the time when the quiz ends (after final submit)
    public int imageCount2 = 0; // setting imageCount to 0, see the changeImageListener method

    /**
     * Fun little method to change between 3 backgrounds
     * When we first click : imageCount is 0 as we initialised it
     * Once we clicked once it changes the background as stated in the first if statement that is true
     * imageCount becomes 1. Second click, first else if block is true, changes background;
     * imageCount becomes 2. Thirs click, second else if block is true, changes background to the initial background set in the xml
     * imageCount becomes 0. So it starts all over again at the next click.
     */
    View.OnClickListener changeImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageView image = getActivity().findViewById(R.id.changeable_image2);
            if (imageCount2 == 0) {
                image.setImageResource(R.drawable.quantum2);
                imageCount2++;
            } else if (imageCount2 == 1) {
                image.setImageResource(R.drawable.quantum3);
                imageCount2++;
            } else if (imageCount2 == 2) {
                image.setImageResource(R.drawable.quantum1);
                imageCount2 = 0; // if this is the 3rd image, make imageCount2 0 to start over frm the first
            }
        }
    };
    /** Huge submit method */
    View.OnClickListener submitAnswerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /** Getting radio buttons, text views and other views to manipulate them */
            RadioButton checkbox1 = getActivity().findViewById(R.id.answer12);
            RadioButton checkbox2 = getActivity().findViewById(R.id.answer22);
            RadioButton checkbox3 = getActivity().findViewById(R.id.answer32);
            RadioButton checkbox4 = getActivity().findViewById(R.id.answer42);
            TextView finalCorrectAnswers = getActivity().findViewById(R.id.final_correct_answers2);
            TextView finalWrongAnswers = getActivity().findViewById(R.id.final_wrong_answers2);
            TextView finalTimeElapsed = getActivity().findViewById(R.id.final_time_elapsed2);
            TextView question = getActivity().findViewById(R.id.question_text_view2);
            TextView questionNumber = getActivity().findViewById(R.id.current_question2);
            View finishedQuiz = getActivity().findViewById(R.id.finished_quiz2);
            /** Handling an error in case user doesn't check any answer OR all questions were answered */
            Toast error = Toast.makeText(getContext(), getString(R.string.no_answer_checked), Toast.LENGTH_LONG);
            Toast errorFinalQuestion = Toast.makeText(getContext(), getString(R.string.final_question), Toast.LENGTH_LONG);
            if (!checkbox1.isChecked() && !checkbox2.isChecked() && !checkbox3.isChecked() && !checkbox4.isChecked()) {
                error.show();
                return; // break out of method if no answers selected
            }

            if (currentQuestion2 == totalQuestions) {
                errorFinalQuestion.show();
            }

            /** When we click the submit button we check to see what question this is, starting from 0
             * GET the next question and answers so once we click submit we can SET them in our TextViews
             * Check to see if the selected answer's (isSelected) text string is equal to the current correct answer
             * If it is, add 1 to our score
             */
            if (currentQuestion2 == 0) {
                // Get the next question and answers to set them after we're done with this current question
                String nextAnswerOneText = getResources().getString(R.string.correct_answer1_2_quantum);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_2_quantum);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_2_quantum);
                String nextAnswerFourText = getResources().getString(R.string.trick_answer4_2_quantum);
                String nextQuestion = getString(R.string.question_two_quantum);
                // Get the correct answer for the current question to compare it with the selected radio button
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_1_quantum);
                // Comparing the selected radio button (whichever the user might choose) with the correct answer
                // Add 1 to the score if they match
                if (checkbox1.isChecked()) {
                    if (checkbox1.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox2.isChecked()) {
                    if (checkbox2.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox3.isChecked()) {
                    if (checkbox3.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox4.isChecked()) {
                    if (checkbox4.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                // Now that we are done with the current question and answers set the next set of Q&A
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                // We are done with this question, add 1 so we can go to the next one
                currentQuestion2++;
                // Also set the bottom text '1 0f 7' to '2 of 7'
                // We pass in the currentQuestion + 1 and totalQuestions for the xml string (see strings.xml)
                questionNumber.setText(getString(R.string.current_question, currentQuestion2 + 1, totalQuestions));
            } else if (currentQuestion2 == 1) {
                // We repeat the same process as above :
                // 1. GET next set of answers and the next question
                // 2. Compare the correct answer to the selected answer - add 1 to the score if they match
                // 3. SET the next answers and next question to the TextViews
                // 4. Add 1 to currentQuestion so we can move to the next step
                String nextAnswerOneText = getResources().getString(R.string.trick_answer1_3_quantum);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_3_quantum);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_3_quantum);
                String nextAnswerFourText = getResources().getString(R.string.correct_answer4_3_quantum);
                String nextQuestion = getString(R.string.question_three_quantum);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer1_2_quantum);
                if (checkbox1.isChecked()) {
                    if (checkbox1.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox2.isChecked()) {
                    if (checkbox2.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox3.isChecked()) {
                    if (checkbox3.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox4.isChecked()) {
                    if (checkbox4.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion2++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion2 + 1, totalQuestions));
            } else if (currentQuestion2 == 2) {
                String nextAnswerOneText = getResources().getString(R.string.trick_answer1_4_quantum);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_4_quantum);
                String nextAnswerThreeText = getResources().getString(R.string.correct_answer3_4_quantum);
                String nextAnswerFourText = getResources().getString(R.string.trick_answer4_4_quantum);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_3_quantum);
                if (checkbox1.isChecked()) {
                    if (checkbox1.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox2.isChecked()) {
                    if (checkbox2.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox3.isChecked()) {
                    if (checkbox3.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox4.isChecked()) {
                    if (checkbox4.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                String nextQuestion = getString(R.string.question_four_quantum);
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion2++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion2 + 1, totalQuestions));
            } else if (currentQuestion2 == 3) {
                String nextAnswerOneText = getResources().getString(R.string.trick_answer1_5_quantum);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_5_quantum);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_5_quantum);
                String nextAnswerFourText = getResources().getString(R.string.correct_answer4_5_quantum);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer3_4_quantum);
                if (checkbox1.isChecked()) {
                    if (checkbox1.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox2.isChecked()) {
                    if (checkbox2.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox3.isChecked()) {
                    if (checkbox3.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox4.isChecked()) {
                    if (checkbox4.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                String nextQuestion = getString(R.string.question_five_quantum);
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion2++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion2 + 1, totalQuestions));
            } else if (currentQuestion2 == 4) {
                String nextAnswerOneText = getResources().getString(R.string.correct_answer1_6_quantum);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_6_quantum);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_6_quantum);
                String nextAnswerFourText = getResources().getString(R.string.trick_answer4_6_quantum);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_5_quantum);
                if (checkbox1.isChecked()) {
                    if (checkbox1.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox2.isChecked()) {
                    if (checkbox2.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox3.isChecked()) {
                    if (checkbox3.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox4.isChecked()) {
                    if (checkbox4.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                String nextQuestion = getString(R.string.question_six_quantum);
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion2++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion2 + 1, totalQuestions));
            } else if (currentQuestion2 == 5) {
                String nextAnswerOneText = getResources().getString(R.string.trick_answer1_7_quantum);
                String nextAnswerTwoText = getResources().getString(R.string.trick_answer2_7_quantum);
                String nextAnswerThreeText = getResources().getString(R.string.trick_answer3_7_quantum);
                String nextAnswerFourText = getResources().getString(R.string.correct_answer4_7_quantum);
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer1_6_quantum);
                if (checkbox1.isChecked()) {
                    if (checkbox1.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox2.isChecked()) {
                    if (checkbox2.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox3.isChecked()) {
                    if (checkbox3.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox4.isChecked()) {
                    if (checkbox4.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                String nextQuestion = getString(R.string.question_seven_quantum);
                checkbox1.setText(nextAnswerOneText);
                checkbox2.setText(nextAnswerTwoText);
                checkbox3.setText(nextAnswerThreeText);
                checkbox4.setText(nextAnswerFourText);
                question.setText(nextQuestion);
                currentQuestion2++;
                questionNumber.setText(getString(R.string.current_question, currentQuestion2 + 1, totalQuestions));
            } else if (currentQuestion2 == 6) {
                // This is the final question so we don't have any more questions and answers to get
                // Now we compare the selected answer with the correct answer as we did before
                // And we display the finished screen with the results
                String currentCorrectAnswer = getResources().getString(R.string.correct_answer4_7_quantum);
                if (checkbox1.isChecked()) {
                    if (checkbox1.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox2.isChecked()) {
                    if (checkbox2.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox3.isChecked()) {
                    if (checkbox3.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                if (checkbox4.isChecked()) {
                    if (checkbox4.getText().toString() == currentCorrectAnswer) {
                        quizScore2++;
                    }
                }
                currentQuestion2++; // now we make currentQuestion = to totalQuestions(7) so the toast error will show up if we click submit
                wrongAnswers = totalQuestions - quizScore2; // correct answers : quizScore; wrong answers : totalQuestions - quizScore
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
                finalCorrectAnswers.setText("" + quizScore2);
                finalWrongAnswers.setText("" + wrongAnswers);
                finalTimeElapsed.setText("" + elapsedSeconds + " sec");
            }
        }
    };
    /**
     * Reset everything back to normal
     */
    View.OnClickListener resetQuizListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /** Reset the timer only at the end or before the second question to limit cheating */
            if (currentQuestion2 <= 1 || currentQuestion2 == 7) {
                timeStart = System.currentTimeMillis();
            }
            currentQuestion2 = 0;
            quizScore2 = 0;
            RadioButton checkbox1 = getActivity().findViewById(R.id.answer12);
            RadioButton checkbox2 = getActivity().findViewById(R.id.answer22);
            RadioButton checkbox3 = getActivity().findViewById(R.id.answer32);
            RadioButton checkbox4 = getActivity().findViewById(R.id.answer42);
            View finishedQuiz = getActivity().findViewById(R.id.finished_quiz2);
            TextView question = getActivity().findViewById(R.id.question_text_view2);
            TextView questionNumber = getActivity().findViewById(R.id.current_question2);
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
            questionNumber.setText(getString(R.string.current_question, currentQuestion2 + 1, totalQuestions));
            question.setText(getResources().getString(R.string.question_one_quantum));
        }
    };
    /**
     * Method for the hint
     * Check what current question it is when we click on the hint image
     * and display the appropriate hint with a toast
     */
    View.OnClickListener showHintListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Creating the toast messages
            Toast hint1 = Toast.makeText(getContext(), getString(R.string.question_one_hint_quantum), Toast.LENGTH_LONG);
            Toast hint2 = Toast.makeText(getContext(), getString(R.string.question_two_hint_quantum), Toast.LENGTH_LONG);
            Toast hint3 = Toast.makeText(getContext(), getString(R.string.question_three_hint_quantum), Toast.LENGTH_LONG);
            Toast hint4 = Toast.makeText(getContext(), getString(R.string.question_four_hint_quantum), Toast.LENGTH_LONG);
            Toast hint5 = Toast.makeText(getContext(), getString(R.string.question_five_hint_quantum), Toast.LENGTH_LONG);
            Toast hint6 = Toast.makeText(getContext(), getString(R.string.question_six_hint_quantum), Toast.LENGTH_LONG);
            Toast hint7 = Toast.makeText(getContext(), getString(R.string.question_seven_hint_quantum), Toast.LENGTH_LONG);
            // Showing the toasts at the corresponding questions
            if (currentQuestion2 == 0) {
                hint1.show();
            } else if (currentQuestion2 == 1) {
                hint2.show();
            } else if (currentQuestion2 == 2) {
                hint3.show();
            } else if (currentQuestion2 == 3) {
                hint4.show();
            } else if (currentQuestion2 == 4) {
                hint5.show();
            } else if (currentQuestion2 == 5) {
                hint6.show();
            } else {
                hint7.show();
            }
        }
    };
}
