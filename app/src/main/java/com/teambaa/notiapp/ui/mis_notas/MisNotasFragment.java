package com.teambaa.notiapp.ui.mis_notas;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teambaa.notiapp.R;
import com.teambaa.notiapp.vistaCards.Nota;
import com.teambaa.notiapp.vistaCards.RecyclerViewAdaptador;

import java.util.ArrayList;
import java.util.List;

import SQL.SQLite;

public class MisNotasFragment extends Fragment
{

    private RecyclerView recyclerView;
    private RecyclerViewAdaptador recyclerViewAdaptador;
    private MisNotasViewModel misNotasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        misNotasViewModel =
                ViewModelProviders.of(this).get(MisNotasViewModel.class);
        View root = inflater.inflate(R.layout.mis_notas, container, false);


        SQLite sqlite;
        //base de datos
        sqlite = new SQLite(getContext());
        sqlite.abrir();
        Cursor cursor = sqlite.getNotas();
        recyclerView = root.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdaptador = new RecyclerViewAdaptador(obtenerNotas(cursor));
        recyclerView.setAdapter(recyclerViewAdaptador);
        //ArrayList<String> reg = sqlite.getNotas(cursor);
        sqlite.cerrar();
        return root;
    }

    public List<Nota> obtenerNotas(Cursor cursor){
        List<Nota> notas = new ArrayList<>();
        String id="",prioridad="";
        String titulo="", nota="";
        if (cursor.moveToFirst())
        {
            do
            {
                id += cursor.getString(0);
                titulo += cursor.getString(1);
                nota += cursor.getString(2);
                prioridad += cursor.getString(3);
                notas.add(new Nota(id, titulo, nota, prioridad));
                id="";
                prioridad="";
                titulo="";
                nota="";
            } while (cursor.moveToNext());
        }
        return notas;
    }


}