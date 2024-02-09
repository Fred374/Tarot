package com.example.tarot;

import android.app.Application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CardUtil {

    public static void seedDatabase(Application app) {
        CardRepository repo = new CardRepository(app);
        ExecutorService exec = Executors.newFixedThreadPool(4);

        try {
            InputStream in = app.getAssets().open("cards.csv");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line;
            List<Card> cards = new ArrayList<>();
            System.out.println("\n\n\n\n HELLO \n\n\n\n");
            while ((line = buffer.readLine()) != null) {
                System.out.println("\n" + line + "\n");
                String[] str = line.split(",");
                System.out.println("\n" + str[0] + "\n");
                int resId = app.getResources().getIdentifier(str[2], "drawable", app.getPackageName());
                int resId_large = app.getResources().getIdentifier(str[2] + "_large", "drawable", app.getPackageName());
                cards.add(new Card(Integer.parseInt(str[0]), str[1], resId, resId_large));
            }
            buffer.close();
            in.close();
            exec.execute(() -> {
                repo.insertAll(cards);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
