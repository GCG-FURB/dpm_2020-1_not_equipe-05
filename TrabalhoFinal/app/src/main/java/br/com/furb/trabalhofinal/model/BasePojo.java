package br.com.furb.trabalhofinal.model;

/**
 * Abstração base para um POJO que será persistido.
 */
public abstract class BasePojo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
