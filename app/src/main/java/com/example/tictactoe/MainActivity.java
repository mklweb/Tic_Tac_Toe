package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE1 = "com.example.tictactoe.MESSAGE1";
    public static final String EXTRA_MESSAGE2 = "com.example.tictactoe.MESSAGE2";

    Button bt_1player;
    Button bt_2player;
    Button bt_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_1player = findViewById(R.id.bt_1player);
        bt_1player.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, table.class);
                intent.putExtra(EXTRA_MESSAGE1, 1);
                intent.putExtra(EXTRA_MESSAGE2, getLevel());
                startActivity(intent);
            }
        });
        bt_2player = findViewById(R.id.bt_2player);
        bt_2player.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, table.class);
                intent.putExtra(EXTRA_MESSAGE1, 2);
                intent.putExtra(EXTRA_MESSAGE2, 1);//En dos jugadores el nivel da igual
                startActivity(intent);
            }
        });
        bt_info = findViewById(R.id.bt_info);
        bt_info.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, info.class);
                startActivity(intent);
            }
        });
    }//Fin onCreate

    //DIFICULTAD
    private int getLevel() {
        RadioGroup configDificultad = findViewById(R.id.level);
        //ke radiobutton ha cogido
        int id = configDificultad.getCheckedRadioButtonId();
        int level;
        if (id == R.id.normal) {
            level = 1;
        } else if (id == R.id.insane) {
            level = 2;
        } else level = 0;
        return level;
    }
}
