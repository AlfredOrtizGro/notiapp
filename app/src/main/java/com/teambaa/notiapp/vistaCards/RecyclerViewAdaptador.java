package com.teambaa.notiapp.vistaCards;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.teambaa.notiapp.R;
import com.teambaa.notiapp.ui.modificar_nota.ModificarNotaFragment;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder>
{



    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView prioridad, titulo, nota,id_nota;
        private ImageView icono;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            prioridad = (TextView) itemView.findViewById(R.id.prioridad);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
            nota = (TextView) itemView.findViewById(R.id.nota);
            icono = itemView.findViewById(R.id.icono);

            id_nota = itemView.findViewById(R.id.misnotas_id);

        }

    }

    public List<Nota> notas;

    public RecyclerViewAdaptador(List<Nota> notas)
    {
        this.notas = notas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.nota_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(vista);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position)
    {
        holder.titulo.setText(notas.get(position).getTitulo());
        holder.nota.setText(notas.get(position).getNota());
        switch (Integer.parseInt(notas.get(position).getPrioridad()))
        {
            case 1:
                holder.prioridad.setText("URGENTE");
                holder.icono.setImageResource(R.drawable.start);
                break;
            case 2:
                holder.prioridad.setText("TAREA");
                holder.icono.setImageResource(R.drawable.task);
                break;
            case 3:
                holder.prioridad.setText("RECORDATORIO");
                holder.icono.setImageResource(R.drawable.recordatorio);
                break;
        }
        holder.id_nota.setText("ID: "+notas.get(position).getId());
    }

    @Override
    public int getItemCount()
    {
        return notas.size();
    }


}
