package com.teambaa.notiapp.ui.modificar_nota;

import android.database.Cursor;
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

public class ModificarNotaFragment extends Fragment
{

    private ModificarNotaViewModel modificarNotaViewModel;
    EditText id, titulo, nota;
    Spinner prioridad_nota;
    Button btnGuardar, btnLimpiar, btnBuscar;
    int op;
    public SQLite sqlite;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        modificarNotaViewModel =
                ViewModelProviders.of(this).get(ModificarNotaViewModel.class);
        View root = inflater.inflate(R.layout.modificar_nota, container, false);


        prioridad_nota = root.findViewById(R.id.m_sp_prioridad);
        id = root.findViewById(R.id.m_id);
        titulo = root.findViewById(R.id.m_titulo_nota);
        nota = root.findViewById(R.id.m_nota);
        btnBuscar = root.findViewById(R.id.m_btn_buscar);
        btnGuardar = root.findViewById(R.id.m_btn_guardar);
        btnLimpiar = root.findViewById(R.id.m_btn_limpiar);

        titulo.setVisibility(View.INVISIBLE);
        nota.setVisibility(View.INVISIBLE);
        prioridad_nota.setVisibility(View.INVISIBLE);

        btnGuardar.setVisibility(View.INVISIBLE);

        sqlite = new SQLite(getContext());
        sqlite.abrir();
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.opciones, android.R.layout.simple_spinner_item);
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

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!id.getText().toString().equals("")){
                    if(sqlite.getCantidad(Integer.parseInt(id.getText().toString())).getCount()==1) {
                        int f = Integer.parseInt(id.getText().toString());
                        Cursor cursor = sqlite.getCantidad(f);
                        String g1 = null, g2 = null;
                        int g3=0;
                        if (cursor.moveToFirst()) {
                            do {
                                g1 = cursor.getString(1);
                                g2 = cursor.getString(2);
                                g3 = cursor.getInt(3);
                            } while (cursor.moveToNext());
                        }

                        prioridad_nota.setVisibility(View.VISIBLE);
                        btnGuardar.setVisibility(View.VISIBLE);
                        titulo.setVisibility(View.VISIBLE);
                        nota.setVisibility(View.VISIBLE);
                        titulo.setText(g1.toString());
                        nota.setText(g2.toString());
                        prioridad_nota.setSelection(g3);


                    }else
                        Toast.makeText(getContext(), "Error: No existe ese ID" +
                                "", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Error: No has puesto un ID" +
                            "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!id.getText().toString().equals("")&&!titulo.getText().toString().equals("")&&!nota.getText().toString().equals("") && op!=0){
                    Toast.makeText(getContext(),titulo.getText().toString().toUpperCase()+" "+nota.getText().toString()+" "+prioridad_nota.getSelectedItem().toString()+" ", Toast.LENGTH_LONG).show();
                    sqlite.editarNota(Integer.parseInt(id.getText().toString()),titulo.getText().toString().toUpperCase(),nota.getText().toString(),op);
                    //recuperar id del ultimo registtro y pasa como parmetro

                    Toast.makeText(getContext(), "Registro modificado", Toast.LENGTH_SHORT).show();
                    id.setText("");
                    titulo.setText("");
                    nota.setText("");

                    prioridad_nota.setSelection(0);
                    sqlite.cerrar();

                }else{
                    Toast.makeText(getContext(), "Error: no puede haber campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(MainActivity);
            }
        });

        return root;
    }
}