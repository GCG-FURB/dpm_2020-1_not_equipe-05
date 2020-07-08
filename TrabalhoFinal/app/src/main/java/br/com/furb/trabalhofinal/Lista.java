package br.com.furb.trabalhofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.furb.trabalhofinal.model.EventPojo;
import br.com.furb.trabalhofinal.service.EventService;
import br.com.furb.trabalhofinal.service.GoogleSignInService;

public class Lista extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Activity listaActivity = this;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        EventService eventService = new EventService();
        eventService.getAll()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            return;
                        }
                        List<EventPojo> events = queryDocumentSnapshots.toObjects(EventPojo.class);
                        mAdapter = new EventsAdapter(events);
                        recyclerView.setAdapter(mAdapter);
                    }
                });

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
                GoogleSignInService.getInstance().logOut(listaActivity);
            }
        });
    }

}