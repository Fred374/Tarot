package com.example.tarot;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.Objects;

public class OneCardSpreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_card_spread);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);
                TextView txt = popupView.findViewById(R.id.info_text);
                txt.setText(R.string.one_card_info);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final android.widget.PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                View view = findViewById(R.id.toolbar);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view) {
        Intent i = new Intent(this, CardInformation.class);
        i.putExtra("card_id", (Integer) view.getTag());
        startActivity(i);
    }
}