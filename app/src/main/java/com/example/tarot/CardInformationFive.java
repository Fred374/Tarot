package com.example.tarot;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CardInformationFive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_information);
        Intent i = getIntent();
        int id = i.getIntExtra("card_id", 0);

        CardRepository repo = new CardRepository(getApplication());
        repo.getCard(id).observe(this, card -> {
            if (card != null) {
                TextView name = findViewById(R.id.card_name);
                name.setText(card.get(0).getName());

                ImageView img = findViewById(R.id.card_image);
                Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), card.get(0).getDraw());
                img.setImageDrawable(drawable);

                TextView description = findViewById(R.id.card_description);
                description.setText(card.get(0).getDescription());
            }
        });

    }
}