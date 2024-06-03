package com.example.listviewv20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivityTwo extends AppCompatActivity {

    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        tv2 = (TextView) findViewById(R.id.txtviewtwoActivity);

        String dato = getIntent().getStringExtra("dato");
        tv2.setText("Hola " + dato);
    }

    // Método para el Botton brack && And regreso al activity One

    public void Brack(View view){
        Intent inBrack = new Intent(this, MainActivity.class);

        startActivity(inBrack);
    }
}