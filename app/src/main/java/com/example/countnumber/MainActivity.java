package com.example.countnumber;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mainNum;
    Button btnBlack, btnRed, btnBlue, btnGreen, btnCount, btnReset;
    SharedPreferences pref;
    String mainColor;
    int count;
    private static final String black = "#FF000000";
    private static final String red = "#FF0000";
    private static final String blue = "#0000FF";
    private static final String green = "#339933";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainNum = findViewById(R.id.mainNum);
        btnBlack = findViewById(R.id.btnBlack);
        btnRed = findViewById(R.id.bntRed);
        btnBlue = findViewById(R.id.btnBlue);
        btnGreen = findViewById(R.id.btnGreen);
        btnCount = findViewById(R.id.btnCount);
        btnReset = findViewById(R.id.btnReset);

        btnBlack.setOnClickListener(Orr);
        btnRed.setOnClickListener(Orr);
        btnBlue.setOnClickListener(Orr);
        btnGreen.setOnClickListener(Orr);
        btnCount.setOnClickListener(Start);
        btnReset.setOnClickListener(Reset);

        mainNum.setGravity(Gravity.CENTER_VERTICAL);
        pref = getSharedPreferences("HELLO_SHARED_PREFS", MODE_PRIVATE);
        mainColor = pref.getString("color", "#FFFF00");
        count = pref.getInt("savedNum", 0);
        mainNum.setText(String.valueOf(count));
        mainNum.setTextColor(Color.parseColor(mainColor));

    }

    private View.OnClickListener Reset = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reset();
        }
    };

    public final View.OnClickListener Start = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count = pref.getInt("savedNum", 0);
            changeCount(count);
        }
    };

    public final View.OnClickListener Orr = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btnBlack:
                {
                    changeColor(black);
                    break;
                }
                case R.id.bntRed:
                {
                    changeColor(red);
                    break;
                }
                case R.id.btnBlue:
                {
                    changeColor(blue);
                    break;
                }
                case R.id.btnGreen:
                {
                    changeColor(green);
                    break;
                }
            }
        }
    };

    private void changeColor(String color)
    {
        mainNum.setTextColor(Color.parseColor(color));
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("color", color);
        editor.apply();
    }

    private void changeCount(int num)
    {
        SharedPreferences.Editor editor = pref.edit();
        num = num + 1;
        mainNum.setText(String.valueOf(num));
        editor.putInt("savedNum", num);
        editor.apply();
    }

    private void reset()
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        SharedPreferences.Editor init = pref.edit();
        init.putString("color", "#FFFF00");
        init.putInt("savedNum", 0);
        mainColor = pref.getString("color", "#FFFF00");
        count = pref.getInt("savedNum", 0);
        editor.apply();
        mainNum.setText(String.valueOf(count));
        mainNum.setTextColor(Color.parseColor(mainColor));


    }
}