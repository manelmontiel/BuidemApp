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
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentZonas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentZonas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentZonas extends Fragment {
    private static String[] from = new String[]{"_id","descripcion"};
    private static int[] to = new int[]{R.id.txtZonaId, R.id.txtZonaDescripcion};

    private GestorDatasource bd ;
    private cursorAdapterZonas cAdapter;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_zonas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bd = new GestorDatasource(getActivity());

        Button btnOk = (Button) getActivity().findViewById(R.id.btnNuevaZona);
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), crearZona.class);
                startActivityForResult(i, 1);
            }
        });

        Cursor cursor = bd.viewDataZonas();

        cAdapter = new cursorAdapterZonas(this.getActivity(),R.layout.row_zonas, cursor, from, to, 1, FragmentZonas.this);
        ListView lv = (ListView) getView().findViewById(R.id.lvZonas);
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
                        Intent i = new Intent(getActivity(), modificarZona.class );
                        i.putExtras(bundle);
                        startActivityForResult(i, 1);

                    }
                }
        );
    }

    public void eliminarZona(final long _id) {

        if (bd.checkIfMaquinaEnZona(_id).getCount() <= 0) {
            // Pedimos confirmación
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("¿Desea eliminar la zona?");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    bd.deleteZonas(_id);
                    carregaZonas();
                }
            });

            builder.setNegativeButton("No", null);

            builder.show();
        }else{
            Toast.makeText(getActivity(),"No puedes eliminar la zona si hay una maquina existente en ella.",Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Carreguem totes les zones a lo bestia
            carregaZonas();
        }
    }

    private void carregaZonas() {

        Cursor cursorZonas = null;

        cursorZonas = bd.viewDataZonas();

        // Un cop fet el filtre li diem al cursor que hem canviat les seves dades i que s'actualitzi
        cAdapter.changeCursor(cursorZonas);
        cAdapter.notifyDataSetChanged();
    }
}
