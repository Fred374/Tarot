package com.example.tarot;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.NavigableSet;
import java.util.TreeSet;

public class FiveCardSpreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_card_spread);

        int[] vws = {R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5};
        int[] txtVws = {R.id.card1_text, R.id.card2_text, R.id.card3_text, R.id.card4_text, R.id.card5_text};
        int[] imgVws = {R.id.card1_image, R.id.card2_image, R.id.card3_image, R.id.card4_image, R.id.card5_image};
        NavigableSet<Integer> vals = new TreeSet<>();
        while (vals.size() < 5) {
            vals.add((int) (Math.random() * 78));
        }

        CardRepository repo = new CardRepository(getApplication());
        repo.getCard(vals.pollFirst(), vals.pollFirst(), vals.pollFirst(), vals.pollFirst(), vals.pollFirst()).observe(this, cards -> {
            if (cards != null) {
                for (int i = 0; i < cards.size(); i++) {
                    View vw = findViewById(vws[i]);
                    vw.setTag(cards.get(i).getId());

                    TextView txt = findViewById(txtVws[i]);
                    txt.setText(cards.get(i).getName());

                    ImageView img = findViewById(imgVws[i]);
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), cards.get(i).getDraw());
                    img.setImageDrawable(drawable);
                }
            }
        });
    }

    public void onClick(View view) {
        Intent i = new Intent(this, CardInformation.class);
        i.putExtra("card_id", (Integer) view.getTag());
        startActivity(i);
    }
}