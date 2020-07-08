package br.com.furb.trabalhofinal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação de um evento a ser persistido.
 */
public class EventPojo extends BasePojo {

    String nome;
    String descricao;
    String endereco;
    List<Participant> participants = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

}
