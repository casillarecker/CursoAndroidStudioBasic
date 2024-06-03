package com.example.sqlsdv30;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et_nome, et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_nome = (EditText) findViewById(R.id.txtetname);
        et_content = (EditText) findViewById(R.id.txtmuline);



    }

    //Mëtodo de SQL product save

    public void Product (View view){
        Intent intent = new Intent(this, MainActivitySqlOne.class);
        startActivity(intent);
    }

    // Método para el bóton Guardar
    public void Guardar (View view){
        String name = et_nome.getText().toString();
        String content = et_content.getText().toString();
         try {
             File tarjeSD = Environment.getExternalStorageDirectory();
             Toast.makeText(this, tarjeSD.getPath(),Toast.LENGTH_SHORT).show();
             File rutaArchivo = new File(tarjeSD.getPath(),name);
             OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(name, Activity.MODE_PRIVATE));

             crearArchivo.write(content);
             crearArchivo.flush();
             crearArchivo.close();

             Toast.makeText(this,"Guardado Correctamente", Toast.LENGTH_SHORT).show();
             et_nome.setText("");
             et_content.setText("");
         }catch (IOException e){
             Toast.makeText(this,"No tiene pudo Guardar", Toast.LENGTH_SHORT).show();
         }
    }
    //Método from Consult
    public void Consult (View view){
        String nametwo = et_nome.getText().toString();

        try {

            File tarjetSD = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File(tarjetSD.getPath(),nametwo);
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nametwo));

            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = leerArchivo.readLine();
            String contentComplete = "";

            while (linea != null){
                contentComplete = contentComplete + linea + "\n";
                linea = leerArchivo.readLine();
            }

            leerArchivo.close();
            abrirArchivo.close();
            et_content.setText(contentComplete);


        }catch (IOException e){
            Toast.makeText(this,"Error al leer el Archivo",Toast.LENGTH_SHORT).show();
        }
    }

}