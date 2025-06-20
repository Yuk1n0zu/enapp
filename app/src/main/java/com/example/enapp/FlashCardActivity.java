package com.example.enapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class FlashCardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyFlashCardAdapter adapter;
    List<FlashCard> flashCardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyFlashCardAdapter(flashCardList);
        recyclerView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("flashcards").get().addOnSuccessListener(queryDocumentSnapshots -> {
            flashCardList.clear();
            for (DocumentSnapshot doc : queryDocumentSnapshots) {
                FlashCard flashCard = doc.toObject(FlashCard.class);
                flashCardList.add(flashCard);
            }
            adapter.notifyDataSetChanged();
        });
    }
}


