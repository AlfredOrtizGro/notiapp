package com.teambaa.notiapp.ui.eliminar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teambaa.notiapp.R;

import SQL.SQLite;

public class EliminarFragment extends Fragment
{
    private EditText id;
    private Button btn_eliminar;
    private EliminarViewModel eliminarViewModel;
    private SQLite sqlite;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        eliminarViewModel =
                ViewModelProviders.of(this).get(EliminarViewModel.class);
        View root = inflater.inflate(R.layout.eliminar, container, false);

        id = root.findViewById(R.id.e_id_nota);
        btn_eliminar = root.findViewById(R.id.btn_eliminar);



        btn_eliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                sqlite=new SQLite(getContext());
                sqlite.abrir();
                if (id.getText().toString() != null)
                {
                    sqlite.eliminarNota(id.getText());
                    Toast.makeText(getContext(), "Registro Eliminado", Toast.LENGTH_SHORT).show();
                    id.setText("");
                } else {
                    Toast.makeText(getContext(), "Llene el campo", Toast.LENGTH_SHORT).show();
                }
                sqlite.cerrar();
            }
        });

        return root;
    }
}