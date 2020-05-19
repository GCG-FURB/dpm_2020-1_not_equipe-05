package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    List ceps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText cep = findViewById(R.id.etMain_cep);
        final EditText cepid = findViewById(R.id.etMain_cepid);
        final TextView resposta = findViewById(R.id.etMain_resposta);
        Button btnBuscarCep = findViewById(R.id.btnMain_buscarCep);
        Button btnBuscarCeps = findViewById(R.id.btnMain_buscarCeps);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CEP retorno = new HttpService(cep.getText().toString()).execute().get();
                    ceps.add(retorno);
                    resposta.setText(retorno.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        btnBuscarCeps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Integer id = Integer.parseInt(cepid.getText().toString()) - 1;
                    resposta.setText(ceps.get(id).toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
