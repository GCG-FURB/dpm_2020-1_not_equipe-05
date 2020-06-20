package br.com.furb.trabalhofinal.service;

import br.com.furb.trabalhofinal.model.EventPojo;

/**
 * Serviço de persistência de {@link EventPojo}.
 */
public class EventService extends FirestoreService<EventPojo> {

    @Override
    protected String getCollectionName() {
        return "events";
    }

}
