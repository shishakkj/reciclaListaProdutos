package com.example.recicla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Cadastro extends AppCompatActivity {
    static ArrayList <Produto> listaProdutos;
    EditText nomeProduto, tipoProduto, precoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();
        nomeProduto = findViewById(R.id.nomeProduto);
        tipoProduto = findViewById(R.id.tipoProduto);
        precoProduto = findViewById(R.id.precoProduto);
    }
    public void cadastrar (View view) {
        try {
            String nome = nomeProduto.getText().toString();
            String tipo = tipoProduto.getText().toString();
            float preco = Float.parseFloat(precoProduto.getText().toString());
            if (verificaSeExiste(nome)) {
                Toast.makeText(this, "O produto já foi registrado!", Toast.LENGTH_SHORT).show();
            }
            else{
                Produto p = new Produto(nome, tipo, preco);
                listaProdutos.add(p);
                super.onBackPressed();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Insira um valor válido!", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean verificaSeExiste(String nome){
        for (Produto p : listaProdutos){
            if (nome.equals(p.getNome())){
                return true;
            }
        }
        return false;
    }
}