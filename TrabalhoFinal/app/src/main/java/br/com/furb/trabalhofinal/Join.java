package br.com.furb.trabalhofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Join extends AppCompatActivity {

    private TextView ptEventName;
    private TextView ptEventDesc;
    private TextView ptEventDate;
    private TextView ptEventHour;
    private TextView ptEventAdre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Intent intent = getIntent();
        String eventName = intent.getStringExtra("eventName");
        String eventDesc = intent.getStringExtra("eventDesc");
        String eventDate = intent.getStringExtra("eventDate");
        String eventHour = intent.getStringExtra("eventHour");
        String eventAddress = intent.getStringExtra("eventAddress");

        ptEventName = (TextView) findViewById(R.id.ptEventName);
        ptEventDesc = (TextView) findViewById(R.id.ptEventDesc);
        ptEventDate = (TextView) findViewById(R.id.ptEventDate);
        ptEventHour = (TextView) findViewById(R.id.ptEventHour);
        ptEventAdre = (TextView) findViewById(R.id.ptEventAdre);

        ptEventName.setText(eventName);
        ptEventDesc.setText(eventDesc);
        ptEventDate.setText(eventDate);
        ptEventHour.setText(eventHour);
        ptEventAdre.setText(eventAddress);

        // Botão para retornar para lista de eventos
        Button BtBackToList = (Button) findViewById(R.id.btLBackToList);
        BtBackToList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Join.this, Lista.class);
                startActivity(it);
            }
        });
    }
}