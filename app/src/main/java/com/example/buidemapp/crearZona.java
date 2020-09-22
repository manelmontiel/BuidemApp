package com.example.buidemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class crearZona extends AppCompatActivity {

    long idZona;
    GestorDatasource bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_zona);

        bd = new GestorDatasource(this);

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


        idZona = bd.insertDataZonas(descripcio);

        Intent i = new Intent();
        i.putExtra("id", idZona);
        setResult(RESULT_OK, i);

        finish();
    }

    private void cancelar() {
        Intent i = new Intent();
        i.putExtra("id", idZona);
        setResult(RESULT_CANCELED, i);

        finish();
    }
}
