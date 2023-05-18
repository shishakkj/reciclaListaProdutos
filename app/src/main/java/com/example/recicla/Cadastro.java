package com.example.recicla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cadastro extends AppCompatActivity {
    static ArrayList<Produto> listaProdutos;
    EditText nomeProduto, tipoProduto, precoProduto;
    String existeOuNao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();
        nomeProduto = findViewById(R.id.nomeProduto);
        tipoProduto = findViewById(R.id.tipoProduto);
        precoProduto = findViewById(R.id.precoProduto);
    }

    public void cadastrar(View view) {
        String nome = nomeProduto.getText().toString();
        String tipo = tipoProduto.getText().toString();
        float preco = Float.parseFloat(precoProduto.getText().toString());
        Produto p = new Produto(nome, tipo, preco);
        verificaSeExiste(p);
    }

    public void verificaSeExiste(Produto produto){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(produto.getNome())){
                    Toast.makeText(Cadastro.this, "Esse produto já existe", Toast.LENGTH_SHORT).show();
                }
                else {
                    listaProdutos.add(produto);
                    produto.salvar();
                    Cadastro.super.onBackPressed();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}