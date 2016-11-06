package com.omega.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Omega on 2016-11-02.
 */

public class Item {
    private String imagemUrl, titulo, resumo, data;
    Bitmap imagem;

    public Item(String imagemUrl, String titulo, String resumo, String data) {
        setImagemUrl(imagemUrl);
        this.resumo = resumo;
        this.titulo = titulo;
        this.data = data;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;

        if(imagemUrl != null) {
            HttpURLConnection conexao = null;
            try {
                URL url = new URL(imagemUrl);
                conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestMethod("GET");
                conexao.setDoInput(true);
                conexao.connect();

                InputStream is = conexao.getInputStream();
                setImagem( BitmapFactory.decodeStream(is) );

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(conexao != null){
                    conexao.disconnect();
                }
            }
        }
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
