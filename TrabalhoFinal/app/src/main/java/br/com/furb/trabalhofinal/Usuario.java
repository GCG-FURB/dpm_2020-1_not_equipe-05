package br.com.furb.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.furb.trabalhofinal.ui.login.LoginActivity;

public class Usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        // Botão para finalizar cadastro, onde envia o usuário para a lista de eventos
        Button BtAddUser = (Button) findViewById(R.id.btAddUser);
        BtAddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Usuario.this, Lista.class);
                startActivity(it);
            }
        });

        // Botão para voltar para Login
        Button BtBackToLogin = (Button) findViewById(R.id.btBackToLogin);
        BtBackToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Usuario.this, LoginActivity.class);
                startActivity(it);
            }
        });
    }
}