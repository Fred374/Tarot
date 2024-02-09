package com.example.tarot;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OneCardSpreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_card_spread);
        CardRepository repo = new CardRepository(getApplication());
        repo.getCard((int) (Math.random() * 78)).observe(this, cards -> {
            if (cards != null) {
                TextView txt = findViewById(R.id.card1);
                txt.setText(cards.get(0).getName());
                txt.setCompoundDrawablesWithIntrinsicBounds(0, cards.get(0).getDraw_large(), 0, 0);
            }
        });
    }
}