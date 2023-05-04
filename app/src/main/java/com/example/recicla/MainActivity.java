package com.example.recicla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList <Produto> listaProdutos = new ArrayList<>();
    RecyclerView recycler;
    Adaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recycler = findViewById(R.id.rv);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        cadastroInicial();
        adaptador = new Adaptador(this, listaProdutos, new Adaptador.OnItemClickListener() {
            @Override
            public void onItemClick(Produto p) {
                Toast.makeText(MainActivity.this, p.getNome(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();
    }
    public void telaCadastro (View view){
        Intent i = new Intent(this, Cadastro.class);
        startActivity(i);
        Cadastro.listaProdutos = listaProdutos;
    }
    public void cadastroInicial(){

    }

    @Override
    protected void onResume() {
        super.onResume();
        adaptador.notifyDataSetChanged();
    }
}