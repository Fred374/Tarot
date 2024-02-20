package com.example.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        CardRepository repo = new CardRepository(this.getApplication());
        Executor exec = Executors.newFixedThreadPool(4);
        repo.getAll().observe(this, card -> {
            if (card.size() != 78) {
                exec.execute(repo::deleteAll);
                CardUtil.seedDatabase(this.getApplication());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);
                TextView txt = popupView.findViewById(R.id.info_text);
                txt.setText(R.string.main_info);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
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