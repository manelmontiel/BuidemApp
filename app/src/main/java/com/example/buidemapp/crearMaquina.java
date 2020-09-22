package com.example.buidemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class crearMaquina extends AppCompatActivity {

    long idMaquina;
    GestorDatasource bd;

    /*Spinner workerId = (Spinner) findViewById(R.id.spinner);

    Cursor c = bd.viewIdZonas();

    private static String[] columns = new String[]{"_id"};
    int[] to = new int[] { android.R.id.text1 };

    myAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, columns,to, 0);
    workerId.setAdapter(myAdapter);
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_maquina);

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
        EditText edtCliente;
        EditText edtAdreca;
        EditText edtCodiPostal;
        EditText edtPoblacio;
        EditText edtTelefon;
        EditText edtEmail;
        EditText edtNMaquina;
        EditText edtFecha;
        EditText edtTipoMaquina;
        EditText edtZona;

        // El codi d'article ha d'estar informat i ha de ser únic
        edtCliente = findViewById(R.id.edtCliente);
        String cliente = edtCliente.getText().toString();

        if (cliente.length() == 0) {
            Snackbar.make(findViewById(android.R.id.content), "Los campos cliente, adreça, codi postal, poblacio, numero maquina, tipo maquina y zona han de esta informados..", Snackbar.LENGTH_LONG).show();
            return;
        }

        edtAdreca = findViewById(R.id.edtAdreca);
        String adreca = edtAdreca.getText().toString();

        if (adreca.length() == 0) {
            Snackbar.make(findViewById(android.R.id.content), "Los campos cliente, adreça, codi postal, poblacio, numero maquina, tipo maquina y zona han de esta informados..", Snackbar.LENGTH_LONG).show();
            return;
        }

        edtCodiPostal = findViewById(R.id.edtCodiPostal);
        String codiPostal = edtCodiPostal.getText().toString();

        if (codiPostal.length() == 0) {
            Snackbar.make(findViewById(android.R.id.content), "Los campos cliente, adreça, codi postal, poblacio, numero maquina, tipo maquina y zona han de esta informados..", Snackbar.LENGTH_LONG).show();
            return;
        }

        edtPoblacio = findViewById(R.id.edtPoblacio);
        String poblacio = edtPoblacio.getText().toString();

        if (poblacio.length() == 0) {
            Snackbar.make(findViewById(android.R.id.content), "Los campos cliente, adreça, codi postal, poblacio, numero maquina, tipo maquina y zona han de esta informados..", Snackbar.LENGTH_LONG).show();
            return;
        }

        edtTelefon = findViewById(R.id.edtTelefon);
        String telefon = edtTelefon.getText().toString();

        edtEmail = findViewById(R.id.edtEmail);
        String email = edtEmail.getText().toString();

        edtNMaquina = findViewById(R.id.edtNMaquina);
        String nMaquina = edtNMaquina.getText().toString();

        if (nMaquina.length() == 0) {
            Snackbar.make(findViewById(android.R.id.content), "Los campos cliente, adreça, codi postal, poblacio, numero maquina, tipo maquina y zona han de esta informados..", Snackbar.LENGTH_LONG).show();
            return;
        }

        edtFecha = findViewById(R.id.edtFecha);
        String fecha = edtFecha.getText().toString();

        edtTipoMaquina = findViewById(R.id.edtTipoMaquina);
        String tipoMaquina = edtTipoMaquina.getText().toString();

        if (tipoMaquina.length() == 0) {
            Snackbar.make(findViewById(android.R.id.content), "Los campos cliente, adreça, codi postal, poblacio, numero maquina, tipo maquina y zona han de esta informados..", Snackbar.LENGTH_LONG).show();
            return;
        }

        edtZona = findViewById(R.id.edtZonas);
        String zona = edtZona.getText().toString();


        if (zona.length() == 0) {
            Snackbar.make(findViewById(android.R.id.content), "Los campos cliente, adreça, codi postal, poblacio, numero maquina, tipo maquina y zona han de esta informados..", Snackbar.LENGTH_LONG).show();
            return;
        }

        idMaquina = bd.insertDataMaquinas(cliente, adreca, codiPostal, poblacio, telefon, email, nMaquina, fecha, tipoMaquina, zona);

        Intent i = new Intent();
        i.putExtra("id", idMaquina);
        setResult(RESULT_OK, i);

        finish();
    }

    private void cancelar() {
        Intent i = new Intent();
        i.putExtra("id", idMaquina);
        setResult(RESULT_CANCELED, i);

        finish();
    }
}
