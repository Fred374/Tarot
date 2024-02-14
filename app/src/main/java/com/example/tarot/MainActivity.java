package com.example.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardRepository repo = new CardRepository(this.getApplication());
        Executor exec = Executors.newFixedThreadPool(4);
        repo.getAll().observe(this, card -> {
            if (card.size() != 78) {
                exec.execute(() -> {
                    repo.deleteAll();
                });
                CardUtil.seedDatabase(this.getApplication());
            }
        });
    }

    public void onClick1Card(View view) {
        Intent intent = new Intent(MainActivity.this, OneCardSpreadActivity.class);
        startActivity(intent);
    }

    public void onClick3Card(View view) {
        Intent intent = new Intent(MainActivity.this, ThreeCardSpreadActivity.class);
        startActivity(intent);
    }

    public void onClick5Card(View view) {
        Intent intent = new Intent(MainActivity.this, FiveCardSpreadActivity.class);
        startActivity(intent);
    }
}