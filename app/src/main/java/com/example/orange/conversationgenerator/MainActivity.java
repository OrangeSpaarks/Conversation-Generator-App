package com.example.orange.conversationgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private List<String> starters1 = new ArrayList<>();
    private List<String> starters2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream input = getResources().openRawResource(R.raw.conversation_starters);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("ASCII")));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                starters1.add(line);
            }
        }
        catch (IOException e) {
            Log.wtf("MyApplication", "Error reading data file");
            e.printStackTrace();
        }
    }

    public void generate(View view) {
        if (starters1.isEmpty()) {
            starters1 = starters2;
            starters2 = new ArrayList<>();
        }
        int random = (int)(Math.random() * starters1.size());
        String line = starters1.remove(random);
        starters2.add(line);

        TextView box = findViewById(R.id.textView);
        box.setText(line);
    }
}
