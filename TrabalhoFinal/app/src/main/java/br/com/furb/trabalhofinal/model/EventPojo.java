package br.com.furb.trabalhofinal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação de um evento a ser persistido.
 */
public class EventPojo extends BasePojo {

    String name;
    List<Participant> participants = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
