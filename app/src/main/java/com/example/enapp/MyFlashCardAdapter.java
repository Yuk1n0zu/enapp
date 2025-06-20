package com.example.enapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enapp.R;
import java.util.List;

public class MyFlashCardAdapter extends RecyclerView.Adapter<MyFlashCardAdapter.ViewHolder> {
    private List<FlashCard> flashCards;

    public MyFlashCardAdapter(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flashcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FlashCard flashCard = flashCards.get(position);
        holder.cardFront.setText(flashCard.getWord());
        holder.cardBack.setText(flashCard.getMeaning());
    }

    @Override
    public int getItemCount() {
        return flashCards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardFront, cardBack;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardFront = itemView.findViewById(R.id.card_front);
            cardBack = itemView.findViewById(R.id.card_back);

            float scale = itemView.getResources().getDisplayMetrics().density;
            cardFront.setCameraDistance(8000 * scale);
            cardBack.setCameraDistance(8000 * scale);

            AnimatorSet frontAnim = (AnimatorSet) AnimatorInflater.loadAnimator(itemView.getContext(), R.animator.front_animator);
            AnimatorSet backAnim = (AnimatorSet) AnimatorInflater.loadAnimator(itemView.getContext(), R.animator.back_animator);

            cardFront.setOnClickListener(v -> {
                frontAnim.setTarget(cardFront);
                backAnim.setTarget(cardBack);
                frontAnim.start();
                backAnim.start();
                cardFront.setVisibility(View.GONE);
                cardBack.setVisibility(View.VISIBLE);
            });

            cardBack.setOnClickListener(v -> {
                frontAnim.setTarget(cardBack);
                backAnim.setTarget(cardFront);
                frontAnim.start();
                backAnim.start();
                cardBack.setVisibility(View.GONE);
                cardFront.setVisibility(View.VISIBLE);
            });
        }
    }
}

