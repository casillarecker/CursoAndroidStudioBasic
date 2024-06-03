package com.example.listviewv20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private ListView lv1;

    private String nombres [] = {"Vilma", "Marlene", "Nancy", "Roxana","Jhilda"};
    private String edad [] = {"25","35","28","33","26"};

    private EditText etn,etp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.txtview);
        lv1 = (ListView) findViewById(R.id.lisView);

        etn = (EditText) findViewById(R.id.txtname);
        etp = (EditText) findViewById(R.id.txtpass);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_rey,nombres);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv1.setText("La edad de " + lv1.getItemAtPosition(position) + " es " + edad [position] + " años");
            }
        });



    }

    // Método para ingrasar aun Sitio Web
    public void NavegarIconMensaje (View view){

        Intent intweb = new Intent(this, MainActivityWeb.class);
        //intweb.putExtra("sitio", etn.getText().toString());
        startActivity(intweb);
        Toast.makeText(this, "La Wikipedia de CASILLA." , Toast.LENGTH_SHORT ).show();

    }

    public void ManoCasillas (View view){

        Toast.makeText(this, "La Mano de CASILLA." , Toast.LENGTH_SHORT ).show();
    }

    public void ManoDimeAction(View view){
        Intent intent = new Intent(this, MainActivityBitacora.class);
        startActivity(intent);
        Toast.makeText(this,"Aqui probando", Toast.LENGTH_SHORT).show();
    }

    public void Registrar (View view) {

        String name = etn.getText().toString();
        String pass = etp.getText().toString();

        if (name.length() == 0) {

            Toast.makeText(this, "Debes de ingresar un nombre", Toast.LENGTH_SHORT).show();
        }if (pass.length() == 0 ){
            Toast.makeText(this, "Debes de ingresar un password", Toast.LENGTH_SHORT).show();
        }
        if (name.length() !=0 && pass.length() !=0){
            Toast.makeText(this, "El registro es correcto", Toast.LENGTH_SHORT).show();
        }
    }

    // Método el Botón Siguiente && Método Parámetros enviar un texto a otra activity

    public void nextEnviar (View view){
        Intent innext = new Intent(this, MainActivityTwo.class);
        innext.putExtra("dato",etn.getText().toString());
        startActivity(innext);
    }





}