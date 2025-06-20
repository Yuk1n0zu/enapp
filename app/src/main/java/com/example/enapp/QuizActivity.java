package com.example.enapp;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestion;
    private Button btnA, btnB, btnC, btnD;
    private List<Question> questionList = new ArrayList<>();
    private int currentIndex = 0;
    private Question currentQ;
    private Map<Button, String> answerMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("questions").get().addOnSuccessListener(snapshot -> {
            for (DocumentSnapshot doc : snapshot.getDocuments()) {
                Question q = doc.toObject(Question.class);
                questionList.add(q);
            }
            if (!questionList.isEmpty()) showQuestion(questionList.get(currentIndex));
        });

        btnA.setOnClickListener(v -> checkAnswer(answerMap.get(btnA)));
        btnB.setOnClickListener(v -> checkAnswer(answerMap.get(btnB)));
        btnC.setOnClickListener(v -> checkAnswer(answerMap.get(btnC)));
        btnD.setOnClickListener(v -> checkAnswer(answerMap.get(btnD)));
    }

    private void showQuestion(Question q) {
        currentQ = q;
        tvQuestion.setText(q.getQuestion());

        // Shuffle answer options
        List<String> options = new ArrayList<>();
        options.add(q.getOptionA());
        options.add(q.getOptionB());
        options.add(q.getOptionC());
        options.add(q.getOptionD());
        Collections.shuffle(options);

        // Map shuffled options to buttons
        btnA.setText(options.get(0));
        btnB.setText(options.get(1));
        btnC.setText(options.get(2));
        btnD.setText(options.get(3));

        // Xác định đúng sai để checkAnswer()
        answerMap.clear();
        for (int i = 0; i < 4; i++) {
            String correctAnswer = getCorrectText(currentQ.getCorrect());
            answerMap.put(
                    i == 0 ? btnA : i == 1 ? btnB : i == 2 ? btnC : btnD,
                    options.get(i).equals(correctAnswer) ? currentQ.getCorrect() : "X" // "X" là sai
            );
        }
    }

    private void checkAnswer(String answer) {
        if (answer.equals(currentQ.getCorrect())) {
            Toast.makeText(this, "✅ Đúng rồi!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "❌ Sai rồi!", Toast.LENGTH_SHORT).show();
        }

        // Tiếp câu tiếp theo
        currentIndex++;
        if (currentIndex < questionList.size()) {
            new Handler().postDelayed(() -> showQuestion(questionList.get(currentIndex)), 1000);
        } else {
            Toast.makeText(this, "🎉 Hoàn thành!", Toast.LENGTH_LONG).show();
        }
    }

    private String getCorrectText(String correctLetter) {
        switch (correctLetter) {
            case "A": return currentQ.getOptionA();
            case "B": return currentQ.getOptionB();
            case "C": return currentQ.getOptionC();
            case "D": return currentQ.getOptionD();
            default: return "";
        }
    }
}
