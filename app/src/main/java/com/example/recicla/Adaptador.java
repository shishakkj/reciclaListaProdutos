package com.example.recicla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {
    public Adaptador(Context context, ArrayList<Produto> lista, OnItemClickListener listener) {
        this.context = context;
        this.lista = lista;
        this.listener = listener;
    }

    Context context;
    ArrayList<Produto> lista;
    Adaptador.OnItemClickListener listener;


    @NonNull
    @Override
    public Adaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
        return new Adaptador.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.MyViewHolder holder, int position) {
        Produto p = lista.get(position);
        holder.nome.setText(p.getNome());
        holder.tipo.setText(p.getTipo());
        holder.preco.setText("R$"+p.getPreco());
        holder.itemView.setOnClickListener(view ->{
            listener.onItemClick(p);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public interface OnItemClickListener {
        void onItemClick(Produto p);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, tipo, preco;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            tipo = itemView.findViewById(R.id.tipo);
            preco = itemView.findViewById(R.id.preco);
        }
    }
}
