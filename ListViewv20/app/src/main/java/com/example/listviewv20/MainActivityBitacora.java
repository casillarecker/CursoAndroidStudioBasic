package com.example.listviewv20;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivityBitacora extends AppCompatActivity {

    private EditText et1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_bitacora);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et1 = (EditText) findViewById(R.id.txtbit);

        String archivos [] = fileList();
        if (ArchivoExiste(archivos,"bitacora.txt")){
            try {

                InputStreamReader file = new InputStreamReader(openFileInput("bitavora.txt"));
                BufferedReader reader = new BufferedReader(file);
                String linea = reader.readLine();
                String bitabocoraComplete = "";

                while (linea!=null){
                    bitabocoraComplete = bitabocoraComplete + linea + "\n";
                    linea = reader.readLine();
                }
                reader.close();
                file.close();
                et1.setText(bitabocoraComplete);

            }catch (IOException e){

            }

            }

    }

    // Método de de archivo
    private  boolean ArchivoExiste (String archivos [], String NombreArchivo){
        for (int i=0;i<archivos.length;i++)
            if (NombreArchivo.equals(archivos[i]));
            return true;
    }

    //Método para guardar
    public void GuardarBitacora (View view){
        try {
            OutputStreamWriter files = new OutputStreamWriter(openFileOutput("bitavora.txt", Activity.MODE_PRIVATE));
            files.write(et1.getText().toString());
            files.flush();
            files.close();
        }catch (IOException e){

        }
        Toast.makeText(this,"El bittacora fue guardado correctamente", Toast.LENGTH_SHORT).show();
    }
}