package br.com.furb.trabalhofinal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.furb.trabalhofinal.model.EventPojo;
import br.com.furb.trabalhofinal.model.Participant;
import br.com.furb.trabalhofinal.service.EventService;

public class MainActivity extends AppCompatActivity {

    private Button addEvent;
    private TextView eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addEvent = findViewById(R.id.bAddEvent);
        eventName = findViewById(R.id.ptEventName);

        addEvent.setOnClickListener(addEventListener);
    }

    private View.OnClickListener addEventListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addEvent();
        }
    };

    public void addEvent() {
        EventPojo event = new EventPojo();
        event.setName(eventName.getText().toString());

        Participant participant1 = new Participant();
        participant1.setName("teste");
        Participant participant2 = new Participant();
        participant2.setName("teste2");

        event.getParticipants().add(participant1);
        event.getParticipants().add(participant2);

        EventService eventService = new EventService();
        eventService.put(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        eventService.getAll()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            return;
                        }
                        List<EventPojo> events = queryDocumentSnapshots.toObjects(EventPojo.class);
                        events.forEach(e -> Log.d("teste", e.toString()));
                    }
                });
    }
}