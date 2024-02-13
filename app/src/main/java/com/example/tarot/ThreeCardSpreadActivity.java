package com.example.tarot;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.NavigableSet;
import java.util.TreeSet;

public class ThreeCardSpreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_card_spread);

        int[] txtVws = {R.id.card1, R.id.card2, R.id.card3};
        int[] imgVws = {R.id.card1_image, R.id.card2_image, R.id.card3_image};
        NavigableSet<Integer> vals = new TreeSet();
        while (vals.size() < 3) {
            vals.add((int) (Math.random() * 78));
        }

        CardRepository repo = new CardRepository(getApplication());
        repo.getCard(vals.pollFirst(), vals.pollFirst(), vals.pollFirst()).observe(this, cards -> {
            if (cards != null) {
                for (int i = 0; i < cards.size(); i++) {
                    TextView txt = findViewById(txtVws[i]);
                    txt.setText(cards.get(i).getName());

                    ImageView img = findViewById(imgVws[i]);
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), cards.get(i).getDraw());
                    img.setImageDrawable(drawable);
                }
            }
        });
    }
}