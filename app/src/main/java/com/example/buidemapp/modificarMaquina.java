package com.example.buidemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class modificarMaquina extends AppCompatActivity {
    private long id;
    GestorDatasource bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_maquina);

        bd = new GestorDatasource(this);

        id = this.getIntent().getExtras().getLong("id");

        Log.d("idIcon", String.valueOf(id));
        Cursor datos = bd.taskMaquinas(id);
        datos.moveToFirst();

        // Carreguem les dades en la interfície
        EditText tv;

        tv = (EditText) findViewById(R.id.edtClienteMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_CLIENT)));

        tv = (EditText) findViewById(R.id.edtDireccionMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_ADRECA)));

        tv = (EditText) findViewById(R.id.edtCodiPostalMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_CODI_POSTAL)));

        tv = (EditText) findViewById(R.id.edtPoblacionMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_POBLACIO)));

        tv = (EditText) findViewById(R.id.edtTelefonoMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_TELEFON)));

        tv = (EditText) findViewById(R.id.edtEmailMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_EMAIL)));

        tv = (EditText) findViewById(R.id.edtNMaquinaMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_NUMERO)));

        tv = (EditText) findViewById(R.id.edtFechaMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_FECHA)));

        tv = (EditText) findViewById(R.id.edtTipoMaquinaMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_TIPO)));

        tv = (EditText) findViewById(R.id.edtZonaMod);
        tv.setText(datos.getString(datos.getColumnIndex(GestorDatasource.MAQUINAS_ZONA)));

        Button btnOk = (Button) findViewById(R.id.btnAceptarMod);
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                aceptar();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.btnCancelarMod);
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
        EditText edtDireccion;
        EditText edtCodiPostal;
        EditText edtPoblacion;
        EditText edtTelefono;
        EditText edtEmail;
        EditText edtNMaquina;
        EditText edtFecha;
        EditText edtTipoMaquina;
        EditText edtZona;

        // El codi d'article ha d'estar informat i ha de ser únic
        edtCliente = findViewById(R.id.edtClienteMod);
        String cliente = edtCliente.getText().toString();

        edtDireccion = findViewById(R.id.edtDireccionMod);
        String direccion = edtDireccion.getText().toString();

        edtCodiPostal = findViewById(R.id.edtCodiPostalMod);
        String codiPostal = edtCodiPostal.getText().toString();

        edtPoblacion = findViewById(R.id.edtPoblacionMod);
        String poblacion = edtPoblacion.getText().toString();

        edtTelefono = findViewById(R.id.edtTelefonoMod);
        String telefono = edtTelefono.getText().toString();

        edtEmail = findViewById(R.id.edtEmailMod);
        String email = edtEmail.getText().toString();

        edtNMaquina = findViewById(R.id.edtNMaquinaMod);
        String nMaquina = edtNMaquina.getText().toString();

        edtFecha = findViewById(R.id.edtFechaMod);
        String fecha = edtFecha.getText().toString();

        edtTipoMaquina = findViewById(R.id.edtTipoMaquinaMod);
        String tipo = edtTipoMaquina.getText().toString();

        edtZona = findViewById(R.id.edtZonaMod);
        String zona = edtZona.getText().toString();


        bd.updateMaquinas(id, cliente, direccion, codiPostal, poblacion, telefono, email, nMaquina, fecha, tipo, zona);

        setResult(RESULT_OK);

        finish();
    }

    private void cancelar() {

        setResult(RESULT_CANCELED);

        finish();
    }


}
