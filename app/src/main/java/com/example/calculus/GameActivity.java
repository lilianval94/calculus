package com.example.calculus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity
{

    private TextView questionTextView;
    private TextView difficultyTextView;
    private Button[] answerButtons;
    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionTextView = findViewById(R.id.text_question);
        difficultyTextView = findViewById(R.id.text_difficulty);
        answerButtons = new Button[4];
        answerButtons[0] = findViewById(R.id.button_answer1);
        answerButtons[1] = findViewById(R.id.button_answer2);
        answerButtons[2] = findViewById(R.id.button_answer3);
        answerButtons[3] = findViewById(R.id.button_answer4);

        // get difficulty from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("difficulty"))
        {
            String difficulty = intent.getStringExtra("difficulty");
            difficultyTextView.setText(getDifficultyText(difficulty));

            generateQuestion(difficulty);
            showQuestion();
        }

        Button exitButton = findViewById(R.id.button_finish);
        exitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    private String getDifficultyText(String difficulty)
    {
        if (difficulty.equals("easy"))
        {
            return "Easy";
        }
        else if (difficulty.equals("medium"))
        {
            return "Medium";
        }
        else if (difficulty.equals("hard"))
        {
            return "Hard";
        }
        else
        {
            return "Unknown Difficulty";
        }
    }

    private void generateQuestion(String difficulty)
    {
        Random random = new Random();

        String questionText = "";
        String correctAnswer = "";
        String[] answers = new String[]{"?", "?", "?", "?"};

        int operand1 = 0;
        int operand2 = 0;
        int result = 0;
        int max = 0;
        int min = 0;
        int operator = 0;

        // set maximum ramdom value and random operator according to difficulty
        if (difficulty.equals("easy"))
        {
            max = 10;
            // 50% to get a minus operator
            if ((int)Math.floor(Math.random() * (100 - 0 + 1) + 0) % 2 == 0)
            {
                operator = 1;
            }
        }
        else if (difficulty.equals("medium"))
        {
            max = 100;
            // 10% to get a - operator
            if ((int)Math.floor(Math.random() * (100 - 0 + 1) + 0) % 10 == 0)
            {
                operator = 1;
            }
            // 33% to get a * operator
            else if ((int)Math.floor(Math.random() * (100 - 0 + 1) + 0) % 3 == 0)
            {
                operator = 2;
            }
            // 33% to get a / operator
            else if ((int)Math.floor(Math.random() * (100 - 0 + 1) + 0) % 3 == 0)
            {
                operator = 3;
            }
        }
        else if (difficulty.equals("hard"))
        {
            max = 1000;
            // 10% to get a - operator
            if ((int)Math.floor(Math.random() * (100 - 0 + 1) + 0) % 10 == 0)
            {
                operator = 1;
            }
            // 33% to get a * operator
            else if ((int)Math.floor(Math.random() * (100 - 0 + 1) + 0) % 3 == 0)
            {
                operator = 2;
            }
            // 33% to get a / operator
            else if ((int)Math.floor(Math.random() * (100 - 0 + 1) + 0) % 3 == 0)
            {
                operator = 3;
            }
        }
        else
        {
            max = 0;
        }

        operand1 = random.nextInt((max - min) + 1) + min;
        operand2 = random.nextInt((operand1 - 0) + 1) + 0;

        // operation creation
        if (operator == 0)
        {
            questionText = operand1 + " + " + operand2;
            correctAnswer = Integer.toString(operand1 + operand2);
        }
        else if (operator == 1)
        {
            questionText = operand1 + " - " + operand2;
            correctAnswer = Integer.toString(operand1 - operand2);
        }
        else if (operator == 2)
        {
            questionText = operand1 + " * " + operand2;
            correctAnswer = Integer.toString(operand1 * operand2);
        }
        else if (operator == 3)
        {
            // get operand2 which divides perfectly operand1
            List<Integer> divisors = getDivisors(operand1);
            int rdm = random.nextInt(((divisors.size() - 1) - min) + 1) + min;
            operand2 = divisors.get(rdm);

            questionText = operand1 + " / " + operand2;
            correctAnswer = Integer.toString(operand1 / operand2);
        }
        else
        {
            questionText = "?";
        }

        // give answers with unique answer
        answers = generateAnswers(difficulty, correctAnswer == "" ? 0 :  Integer.parseInt(correctAnswer));
        while (uniqueAnswers(answers))
        {
            Log.i("restart done", "restart done");
            answers = generateAnswers(difficulty, correctAnswer == "" ? 0 :  Integer.parseInt(correctAnswer));
        }

        Log.i("questionText", questionText);
        Log.i("correctAnswer", correctAnswer);
        Log.i("answers", answers[0] + "/" + answers[1] + "/" + answers[2] + "/" + answers[3]);
        Log.i("difficulty", difficulty);
        currentQuestion = new Question(questionText, correctAnswer, answers, difficulty);
    }

    private String[] generateAnswers(String difficulty, int correctAnswer)
    {
        String[] answers = new String[]{"?", "?", "?", "?"};
        int max = 0;
        if (difficulty.equals("easy"))
        {
            max = 8;
        }
        else if (difficulty.equals("medium"))
        {
            max = 50;
        }
        else if (difficulty.equals("hard"))
        {
            max = 100;
        }
        else
        {
            max = 0;
            Log.e("incorrect difficulty", "incorrect difficulty");
        }
        int min = -max;

        int correctPlace = (int)Math.floor(Math.random() * (3 - 0 + 1) + 0);

        for (int i = 0; i <= 3; i++)
        {
            if (i == correctPlace)
            {
                answers[i] = Integer.toString(correctAnswer);
                continue;
            }

            int rdm = new Random().nextInt((max - 0) + 1) + 0;
            String answer = Integer.toString(rdm + correctAnswer);

            answers[i] = answer;
        }

        return answers;
    }

    private Boolean uniqueAnswers(String[] answers)
    {
        Boolean found = false;
        for (int i = 0; i <= 2; i++)
        {
            for (int j = i + 1; j <= 3; j++)
            {
                if (answers[i].equals(answers[j]))
                {
                    Log.i("restart generation", "restart generation");
                    found = true;
                }
            }
        }
        return found;
    }

    private List<Integer> getDivisors(int number)
    {
        List<Integer> divisors = new ArrayList<>();

        for (int i = 1; i <= number; i++)
        {
            if (number % i == 0)
            {
                divisors.add(i);
            }
        }

        return divisors;
    }

    private void showQuestion()
    {
        if (currentQuestion != null)
        {
            // Display question and answers
            questionTextView.setText(currentQuestion.getQuestion());

            String[] answers = currentQuestion.getAnswers();
            for (int i = 0; i < answerButtons.length; i++)
            {
                answerButtons[i].setBackgroundColor(Color.DKGRAY);
                answerButtons[i].setText(answers[i]);
            }

            // Define click event on buttons
            for (Button button : answerButtons) {
                button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        checkAnswer(((Button) v).getText().toString());
                    }
                });
            }
        }
    }

    private void checkAnswer(String selectedAnswer)
    {
        if (currentQuestion != null)
        {
            for (int i = 0; i < answerButtons.length; i++)
            {
                Button button = answerButtons[i];
                if (button.getText().toString().equals(currentQuestion.getCorrectAnswer()))
                {
                    // Correct answer
                    if (button.getText().toString().equals(selectedAnswer))
                    {
                        button.setBackgroundColor(Color.GREEN);
                    }
                    // Incorrect answer
                    else
                    {
                        button.setBackgroundColor(Color.RED);
                        Button selectedButton = (Button) button;
                        selectedButton.setBackgroundColor(Color.GREEN);
                    }
                }
                // wrong answer selected
                else if (button.getText().toString().equals(selectedAnswer))
                {
                    button.setBackgroundColor(Color.RED);
                }
                button.setEnabled(false); // desactivate the buttons
            }

            // generate a new question
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    for (int i = 0; i < answerButtons.length; i++)
                    {
                        Button button = answerButtons[i];
                        button.setBackgroundColor(Color.DKGRAY);
                        button.setEnabled(true);
                    }
                    generateQuestion(currentQuestion.getDifficulty());
                    showQuestion();
                }
            }, 1500);
        }
    }
}
