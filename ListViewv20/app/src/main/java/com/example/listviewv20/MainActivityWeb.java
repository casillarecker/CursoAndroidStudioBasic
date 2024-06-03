package com.example.listviewv20;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityWeb extends AppCompatActivity {

    WebView wv1;
    private EditText et1, et_name,et_dato;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);

        wv1 = (WebView) findViewById(R.id.webView);

        String URL = getIntent().getStringExtra("sitio");
        wv1.setWebViewClient(new WebViewClient());
        wv1.loadUrl("http://" + URL);

        // Aqui vamos reclarar la variable && SharePreferencias del Videos 27

        et1 = (EditText) findViewById(R.id.txt_gmail);
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        et1.setText(preferences.getString("mail", ""));

        // Aqui los Valiables de guardar datos de

        et_name = (EditText) findViewById(R.id.txt_gmail);
        et_dato = (EditText) findViewById(R.id.txt_Line);



    }

    // Método para cerrar el Boton de Ventana
    public void Close (View view){
        finish();
    }

    // Método para el botón guardar
    public void SaveGmail (View view){
        SharedPreferences sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = sharedPreferences.edit();
        obj_editor.putString("mail",et1.getText().toString());
        obj_editor.commit();
    }

    public void Save (View view){

        String name = et_name.getText().toString();
        String date = et_dato.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = sharedPreferences.edit();
        obj_editor.putString(name,date);
        obj_editor.commit();


        Toast.makeText(this, "El contacto a sido guardado", Toast.LENGTH_SHORT).show();

    }

    // Método para el botón buscar o search

    public void Search (View view){
        String nombre = et_name.getText().toString();
        SharedPreferences preferences = getSharedPreferences("agenda",Context.MODE_PRIVATE);
        String date = preferences.getString(nombre, "");
        if (date.length() == 0){
            Toast.makeText(this, "No se encuentra ningún registro", Toast.LENGTH_SHORT).show();
        }else{
            et_dato.setText(date);
            Toast.makeText(this,"Estos son los datos que se guardo", Toast.LENGTH_SHORT).show();
        }
    }


}