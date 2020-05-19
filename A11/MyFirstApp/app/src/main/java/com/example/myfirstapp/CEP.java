package com.example.myfirstapp;

import com.google.gson.annotations.SerializedName;

import java.util.concurrent.atomic.AtomicInteger;

class CEP {

    private static final AtomicInteger proxId = new AtomicInteger(0);
    private int id;
    @SerializedName("cep")
    private String cep;
    @SerializedName("logradouro")
    private String logradouro;
    @SerializedName("complemento")
    private String complemento;
    @SerializedName("bairro")
    private String bairro;
    @SerializedName("localizade")
    private String cidade;
    @SerializedName("uf")
    private String estado;

    public int getId(){
        return this.id;
    }

    public void setId(){
        this.id = proxId.incrementAndGet();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CEP: " + getCep()
                + "\nLogradouro: " + getLogradouro()
                + "\nComplemento: " + getComplemento()
                + "\nBairro: " + getBairro()
                + "\nCidade:" + getCidade()
                + "\nEstado: " + getEstado()
                + "\nID: " + getId();
    }
}
