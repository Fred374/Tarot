package com.example.tarot;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class OneCardSpreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_card_spread);
        CardRepository repo = new CardRepository(getApplication());
        repo.getCard((int) (Math.random() * 78)).observe(this, cards -> {
            if (cards != null) {
                View view = findViewById(R.id.card1);
                view.setTag(cards.get(0).getId());

                TextView txt = findViewById(R.id.card1_text);
                txt.setText(cards.get(0).getName());

                ImageView img = findViewById(R.id.card1_image);
                Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), cards.get(0).getDraw());
                img.setImageDrawable(drawable);
            }
        });
    }

    public void onClick(View view) {
        Intent i = new Intent(this, CardInformation.class);
        i.putExtra("card_id", (Integer) view.getTag());
        startActivity(i);
    }
}