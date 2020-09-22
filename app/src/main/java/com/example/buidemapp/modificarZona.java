package com.example.buidemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class modificarZona extends AppCompatActivity {

    private long id;
    GestorDatasource bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_zona);

        bd = new GestorDatasource(this);

        id = this.getIntent().getExtras().getLong("id");

        Log.d("idIcon", String.valueOf(id));
        Cursor datos = bd.taskZonas(id);
        datos.moveToFirst();

        // Carreguem les dades en la interfície
        EditText tv;

        tv = (EditText) findViewById(R.id.edtNombreZona);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.ZONAS_DESCRIPCION)));

        Button btnOk = (Button) findViewById(R.id.btnAceptar);
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                aceptar();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.btnCancelar);
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancelar();
            }
        });
    }

    private void aceptar() {
        // Validem les dades
        EditText edtDescripcio;

        // El codi d'article ha d'estar informat i ha de ser únic
        edtDescripcio = findViewById(R.id.edtNombreZona);
        String descripcio = edtDescripcio.getText().toString();


        bd.updateZonas(id, descripcio);

        setResult(RESULT_OK);

        finish();
    }

    private void cancelar() {

        setResult(RESULT_CANCELED);

        finish();
    }
}
