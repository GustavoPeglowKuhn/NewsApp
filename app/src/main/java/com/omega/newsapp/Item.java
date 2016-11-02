package com.omega.newsapp;

/**
 * Created by Omega on 2016-11-02.
 */

public class Item {
    private String imagem, titulo, resumo, data;

    public Item(String imagem, String titulo, String resumo, String data) {
        this.imagem = imagem;
        this.resumo = resumo;
        this.titulo = titulo;
        this.data = data;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
