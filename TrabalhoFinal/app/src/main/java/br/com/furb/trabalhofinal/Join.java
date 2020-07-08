package br.com.furb.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // Botão para retornar para lista de eventos
        Button BtBackToList = (Button) findViewById(R.id.btBackToList);
        BtBackToList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Join.this, Lista.class);
                startActivity(it);
            }
        });
    }
}