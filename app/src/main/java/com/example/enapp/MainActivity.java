package com.example.enapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnDictionary, btnFlashCard, btnQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDictionary = findViewById(R.id.btnDictionary);
        btnFlashCard = findViewById(R.id.btnFlashCard);
        btnQuiz = findViewById(R.id.btnQuiz);

        btnDictionary.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DictionaryActivity.class);
            startActivity(i);
        });

        btnFlashCard.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, FlashCardActivity.class);
            startActivity(i);
        });

        btnQuiz.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(i);
        });
    }
}
