package com.example.enapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private Context context;
    private List<Word> wordList;

    public WordAdapter(Context context, List<Word> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.wordText.setText(word.getWord());
        holder.posText.setText(word.getPos());
        holder.defText.setText(word.getDef());

        holder.btnSave.setOnClickListener(v -> {
            Map<String, Object> data = new HashMap<>();
            data.put("word", word.getWord());
            data.put("pos", word.getPos());
            data.put("def", word.getDef());

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("flashcards").add(data)
                    .addOnSuccessListener(documentReference ->
                            Toast.makeText(context, "Đã lưu vào FlashCard", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(context, "Lỗi khi lưu", Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView wordText, posText, defText;
        Button btnSave;

        public WordViewHolder(View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.wordText);
            posText = itemView.findViewById(R.id.posText);
            defText = itemView.findViewById(R.id.defText);
            btnSave = itemView.findViewById(R.id.btnSave);
        }
    }
}
