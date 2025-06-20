package com.example.enapp;

public class Word {
    private String word;
    private String pos;
    private String def;

    public Word() {
        // Bắt buộc cho Firestore
    }

    public Word(String word, String pos, String def) {
        this.word = word;
        this.pos = pos;
        this.def = def;
    }

    // Getter và Setter
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
}
