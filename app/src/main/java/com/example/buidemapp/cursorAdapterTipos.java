package com.example.buidemapp;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class cursorAdapterTipos extends android.widget.SimpleCursorAdapter {
    private FragmentTipoMaquina _fragments;

    private MainActivity _main;

    private  MainActivity oTodoListIcon;
    Context contexto;
    private FragmentTipoMaquina fragmentTipoMaquina;
    private ImageView btnBorrar;

    public cursorAdapterTipos(Context context, int layout, Cursor c, String[] from, int[] to, int flags, FragmentTipoMaquina fragment) {
        super(context, layout, c, from, to, flags);
        fragmentTipoMaquina = fragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final View view = super.getView(position, convertView, parent);

        Cursor tipos = (Cursor) getItem(position);

        btnBorrar = (ImageView) view.findViewById(R.id.imgDeleteTipo);

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

                fragmentTipoMaquina.eliminarTipo(linia.getLong(linia.getColumnIndexOrThrow(GestorDatasource.TIPO_MAQUINA_ID)));
            }
        });

        return view;
    }
}
