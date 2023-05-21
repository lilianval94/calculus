package com.example.calculus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DifficultyActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        Button easyButton = findViewById(R.id.button_easy);
        easyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startGameActivity("easy");
            }
        });

        Button mediumButton = findViewById(R.id.button_play);
        mediumButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startGameActivity("medium");
            }
        });

        Button hardButton = findViewById(R.id.button_hard);
        hardButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startGameActivity("hard");
            }
        });

        Button backButton = findViewById(R.id.button_exit);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    private void startGameActivity(String difficulty)
    {
        Intent intent = new Intent(DifficultyActivity.this, GameActivity.class);
        intent.putExtra("difficulty", difficulty); // give the good difficulty for GameActivity
        startActivity(intent);
    }
}

