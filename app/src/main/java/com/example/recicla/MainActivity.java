package com.example.recicla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

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
        recycler.setAdapter(adaptador);
    }
    public void telaCadastro (View view){
        Intent i = new Intent(this, Cadastro.class);
        startActivity(i);
        Cadastro.listaProdutos = listaProdutos;
    }

    @Override
    protected void onResume() {
        super.onResume();
        carrega();
    }
    public void carrega(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProdutos.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    Produto p = (Produto)ds.getValue(Produto.class);
                    listaProdutos.add(p);
                }
                adaptador = new Adaptador(MainActivity.this, listaProdutos, new Adaptador.OnItemClickListener() {
                    @Override
                    public void onItemClick(Produto p) {
                        Toast.makeText(MainActivity.this, p.getNome(), Toast.LENGTH_SHORT).show();
                    }
                });
                recycler.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}