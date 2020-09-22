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
 * {@link FragmentTipoMaquina.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentTipoMaquina#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTipoMaquina extends Fragment {

    private static String[] from = new String[]{"_id","descripcion"};
    private static int[] to = new int[]{R.id.txtTipoId, R.id.txtTipoDesc};

    private GestorDatasource bd ;
    private cursorAdapterTipos cAdapter;
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
        return inflater.inflate(R.layout.fragment_fragment_tipo_maquina, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bd = new GestorDatasource(getActivity());

        Button btnOk = (Button) getActivity().findViewById(R.id.btnNuevoTipo);
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), crearTipo.class);
                startActivityForResult(i, 1);
            }
        });

        Cursor cursor = bd.viewDataTipoMaquina();

        //cAdapter = new cursorAdapterZonas (this.getActivity()), R.layout.row_zonas,cursor, from, to, 1, FragmentZonas.this);
        cAdapter = new cursorAdapterTipos(this.getActivity(),R.layout.row_tipos, cursor, from, to, 1, FragmentTipoMaquina.this);
        ListView lv = (ListView) getView().findViewById(R.id.lvTipoMaquina);
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
                        Intent i = new Intent(getActivity(), modificarTipo.class );
                        i.putExtras(bundle);
                        startActivityForResult(i, 1);

                    }
                }
        );


    }


    public void eliminarTipo(final long _id) {
        if (bd.checkIfMaquinaEnTipo(_id).getCount() <= 0) {
            // Pedimos confirmación
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("¿Desea eliminar el tipo de maquina?");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    bd.deleteTipoMaquina(_id);
                    carregaTipos();
                }
            });

            builder.setNegativeButton("No", null);

            builder.show();
        }else{
            Toast.makeText(getActivity(),"No puedes eliminar el tipo si hay una maquina existente en él.",Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Carreguem totes les zones a lo bestia
            carregaTipos();
        }
    }

    private void carregaTipos() {

        Cursor cursorTipos = null;

        cursorTipos = bd.viewDataTipoMaquina();

        // Un cop fet el filtre li diem al cursor que hem canviat les seves dades i que s'actualitzi
        cAdapter.changeCursor(cursorTipos);
        cAdapter.notifyDataSetChanged();
    }
}
