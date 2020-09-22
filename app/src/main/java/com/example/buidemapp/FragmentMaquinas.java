package com.example.buidemapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMaquinas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMaquinas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMaquinas extends Fragment {
    //private static String[] from = new String[]{"nom","descripcio"};
    //private static int[] to = new int[]{R.id.nom_lloc, R.id.descripcio_lloc};

    private static String[] from = new String[]{"client","tipo_maquina","zona"};
    private static int[] to = new int[]{R.id.txtNombreCliente, R.id.txtTipoMaquina, R.id.txtZona};

    private GestorDatasource bd ;
    private cursorAdapterMaquinas cAdapter;
   // private GestorDatasource helper;
    public static MainActivity context;

    Button btnAgregar;

    ListView articuloList;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    private SimpleCursorAdapter scTasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fragment_maquinas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bd = new GestorDatasource(getActivity());

        Button btnOk = (Button) getActivity().findViewById(R.id.btnNuevaMaquina);
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), crearMaquina.class);
                startActivityForResult(i, 1);
            }
        });

        Cursor cursor = bd.viewDataMaquinas();

        cAdapter = new cursorAdapterMaquinas (this.getActivity(), R.layout.row_maquinas,cursor, from, to, 1, FragmentMaquinas.this);
        ListView lv = (ListView) getView().findViewById(R.id.lvMaquinas);
        lv.setAdapter(cAdapter);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {
                        Bundle bundle = new Bundle();
                        bundle.putLong("id",id);

                        // modifiquem el id
                        Intent i = new Intent(getActivity(), modificarMaquina.class );
                        i.putExtras(bundle);
                        startActivityForResult(i, 1);

                    }
                }
        );


    }


    public void eliminarMaquina(final long _id) {
        // Pedimos confirmación
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Desea eliminar la maquina?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                bd.deleteMaquinas(_id);
                carregaMaquinas();
            }
        });

        builder.setNegativeButton("No", null);

        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Carreguem totes les zones a lo bestia
            carregaMaquinas();
        }
    }

    private void carregaMaquinas() {

        Cursor cursorMaquinas = null;

        cursorMaquinas = bd.viewDataMaquinas();

        // Un cop fet el filtre li diem al cursor que hem canviat les seves dades i que s'actualitzi
        cAdapter.changeCursor(cursorMaquinas);
        cAdapter.notifyDataSetChanged();
    }

}
