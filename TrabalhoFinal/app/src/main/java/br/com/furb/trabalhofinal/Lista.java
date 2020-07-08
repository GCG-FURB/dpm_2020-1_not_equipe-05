package br.com.furb.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.furb.trabalhofinal.ui.login.LoginActivity;

public class Lista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Botão para a tela de cadastro de Eventos
        Button BtToAddEvent = (Button) findViewById(R.id.btToAddEvent);
        BtToAddEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Lista.this, MainActivity.class);
                startActivity(it);
            }
        });

        // Botão para sair e voltar para Login
        Button BtLogout = (Button) findViewById(R.id.btLogout);
        BtLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Lista.this, LoginActivity.class);
                startActivity(it);
            }
        });
    }

}