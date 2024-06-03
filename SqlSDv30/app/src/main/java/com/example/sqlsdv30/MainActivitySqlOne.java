package com.example.sqlsdv30;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivitySqlOne extends AppCompatActivity {

    EditText et1,et2,et3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_sql_one);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et1 = (EditText) findViewById(R.id.txtid);
        et2 = (EditText) findViewById(R.id.txtdescription);
        et3 = (EditText) findViewById(R.id.txtprecio);


    }

    // Método para dar de alta los productos
    public void Registrar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String codigo = et1.getText().toString();
        String description = et2.getText().toString();
        String precio = et3.getText().toString();

        if (!codigo.isEmpty() && !description.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("description",description);
            registro.put("precio",precio);

            database.insert("articulos",null,registro);
            database.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            Toast.makeText(this, "Registro exitoso",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para consultar un artículo o producto
    public void Serch (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();

        String codigo = et1.getText().toString();
        if (!codigo.isEmpty()){
            Cursor fila = sqLiteDatabase.rawQuery
                    ("select description, precio from articulos where codigo = " + codigo , null );

            if (fila.moveToFirst()){
                et2.setText(fila.getString(0));
                et3.setText(fila.getString(1));
                sqLiteDatabase.close();
            }else{
                Toast.makeText(this, "No existe el artículo", Toast.LENGTH_SHORT).show();
                sqLiteDatabase.close();
            }

        }else{
            Toast.makeText(this , "Debes introducir el código del articulo",Toast.LENGTH_SHORT).show();
        }
    }

    //Método para eliminar un product o Artículo

    public void Eliminar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();

        String codigo = et1.getText().toString();
        if (!codigo.isEmpty()){
            int cant = sqLiteDatabase.delete("articulos","codigo=" + codigo, null);
            sqLiteDatabase.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");

            if (cant == 1 ){
                Toast.makeText(this,"Articulo eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"El artículo no existe",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Debes de introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }

    }
    //Método para modificar un artículo o producto

    public void Modificar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null, 1);
        SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();
        String codigo = et1.getText().toString();
        String description = et2.getText().toString();
        String precio = et3.getText().toString();

        if (!codigo.isEmpty() && !description.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("description",description);
            registro.put("precio",precio);

            int cant = sqLiteDatabase.update("articulos",registro,"codigo=" + codigo , null);
            sqLiteDatabase.close();

            et1.setText("");
            et2.setText("");
            et3.setText("");

            if (cant == 1){
                Toast.makeText(this,"Artículo modificado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this ,"El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}