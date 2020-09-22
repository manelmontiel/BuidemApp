package com.example.buidemapp;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class cursorAdapterMaquinas extends android.widget.SimpleCursorAdapter{
    private FragmentTipoMaquina _fragments;

    private MainActivity _main;

    private  MainActivity oTodoListIcon;
    Context contexto;
    private FragmentMaquinas fragmentMaquinas;

    public cursorAdapterMaquinas(Context context, int layout, Cursor c, String[] from, int[] to, int flags, FragmentMaquinas fragment) {
        super(context, layout, c, from, to, flags);
        //_fragments = (FragmentTipoMaquina) fragment;
        /*oTodoListIcon = (MainActivity) context;
        contexto = context;
        _main = (MainActivity) context;*/
        fragmentMaquinas = fragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final View view = super.getView(position, convertView, parent);

        Cursor maquinas = (Cursor) getItem(position);

        ImageView btnBorrar = view.findViewById(R.id.imgDeleteMaq);

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Busquem la linia a eliminar
                View row = (View) v.getParent();
                // Busquem el listView per poder treure el numero de la fila
                ListView lv = (ListView) row.getParent().getParent();
                // Busco quina posicio ocupa la Row dins de la ListView
                int position = lv.getPositionForView(row);
                // Carrego la linia del cursor de la posició.
                Cursor linia = (Cursor) getItem(position);

                fragmentMaquinas.eliminarMaquina(linia.getLong(linia.getColumnIndexOrThrow(GestorDatasource.MAQUINAS_ID)));
            }
        });

        return view;
    }

}
