package com.teambaa.notiapp.ui.crear_nota;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teambaa.notiapp.R;

import SQL.SQLite;

public class CrearNotaFragment extends Fragment
{

    private CrearNotaViewModel crearNotaViewModel;

    EditText id, titulo, nota;
    Spinner prioridad_nota;
    Button btnGuardar, btnLimpiar;
    int op=0;
    public SQLite sqlite;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        crearNotaViewModel =
                ViewModelProviders.of(this).get(CrearNotaViewModel.class);
        View root = inflater.inflate(R.layout.crear_nota, container, false);


        prioridad_nota = root.findViewById(R.id.sp_prioridad);
        id = root.findViewById(R.id.c_id_nota);
        titulo = root.findViewById(R.id.c_titulo_nota);
        nota = root.findViewById(R.id.c_nota);
        btnGuardar = root.findViewById(R.id.btn_crear_guardar);
        btnLimpiar = root.findViewById(R.id.btn_crear_limpiar);


        sqlite = new SQLite(getContext());
        sqlite.abrir();
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.opciones,
                android.R.layout.simple_spinner_item
        );
        prioridad_nota.setAdapter(adapter);
        prioridad_nota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String opcion = String.valueOf(prioridad_nota.getSelectedItemId());
                op = Integer.parseInt(opcion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!titulo.getText().toString().equals("") &&
                        !nota.getText().toString().equals("") && op!=0)
                {
                    //dentro de if
                    Toast.makeText(getContext(), String.valueOf(op) + " " +
                            titulo.getText().toString().toUpperCase() + " " +
                            nota.getText().toString().toUpperCase(), Toast.LENGTH_SHORT).show();
                    if (sqlite.agregarNota(Integer.parseInt(id.getText().toString()), titulo.getText().toString().toUpperCase(),
                            nota.getText().toString(),
                            String.valueOf(op)
                    ))
                    {
                        //Dentro if agregar registro
                        Toast.makeText(getContext(), "REGISTRO AÑADIDO", Toast.LENGTH_SHORT).show();

                        //Limpiar campos
                        id.setText("");
                        titulo.setText("");
                        nota.setText("");
                        prioridad_nota.setSelection(0);
                    } else
                    {
                        Toast.makeText(getContext(),
                                "Error: compruebe que los datos sean correctos",
                                Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(getContext(),
                            "Error: no puede haber campos vacios",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                id.setText("");
                titulo.setText("");
                nota.setText("");
                prioridad_nota.setSelection(0);
            }
        });
        return root;
    }
}