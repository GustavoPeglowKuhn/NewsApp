package com.omega.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Item {
    private static HashMap<String,Bitmap> imageList = new HashMap<>();

    private String imagemUrl, titulo, resumo, data;
    private ImageView imageView;

    public Item(String imagemUrl, String titulo, String resumo, String data) {
        setImagemUrl(imagemUrl);
        this.titulo = titulo;
        this.resumo = resumo;
        this.data = data;
        this.imageView = null;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        if(imagemUrl == null) {
            new ImageTask().execute();
        } else {
            imageView.setImageBitmap(imageList.get(imagemUrl));
        }
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        if( imagemUrl!=null && !imagemUrl.equals(this.imagemUrl) ) {
            this.imagemUrl = imagemUrl;

            if(imageList.containsKey(imagemUrl)){
                imageView.setImageBitmap(imageList.get(imagemUrl));
            } else {
                new ImageTask().execute();
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

    class ImageTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... params) {
            if(imagemUrl == null)
                return null;
            HttpURLConnection conexao = null;
            Bitmap result = null;
            try {
                URL url = new URL(imagemUrl);
                conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestMethod("GET");
                conexao.setDoInput(true);
                conexao.connect();

                InputStream is = conexao.getInputStream();
                result = ( BitmapFactory.decodeStream(is) );

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(conexao != null){
                    conexao.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if(result != null) {
                imageList.put(imagemUrl, result);
                //imagem = result;
                if(imageView != null)
                    imageView.setImageBitmap(result);
            }
            super.onPostExecute(result);
        }
    }
}

