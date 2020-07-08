package br.com.furb.trabalhofinal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação de um evento a ser persistido.
 */
public class EventPojo extends BasePojo {

    String nome;
    String descricao;
    String data;
    String horario;
    String endereco;
    String cep;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

}
