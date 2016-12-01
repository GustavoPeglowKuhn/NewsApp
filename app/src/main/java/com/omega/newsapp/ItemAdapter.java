package com.omega.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        Item item = getItem(position);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.item_imagem);
        item.setImageView(imageView);
        //new ImageTask().execute(item);
        //imagem.setImageBitmap(item.getImagem());
        //imagem.setImageDrawable();    ??      see:    http://www.devmedia.com.br/utilizando-imageview-no-android/27783
        //imagem.setImageURI();         ??
        //if(item.getImagemUrl()!=null)
        //    imageView.setImageURI( Uri.parse( item.getImagemUrl() ) );


        TextView titulo = (TextView) itemView.findViewById(R.id.item_titulo);
        titulo.setText(item.getTitulo());

        TextView resumo = (TextView) itemView.findViewById(R.id.item_resumo);
        resumo.setText(item.getResumo());

        TextView data = (TextView) itemView.findViewById(R.id.item_data);
        data.setText(item.getData());

        return itemView;
    }


    /*class ImageTask extends AsyncTask<Item, Void, Bitmap> {

        Item item = null;

        @Override
        protected Bitmap doInBackground(Item... params) {
             item = params[0];
            if (item  == null)
                return null;

            HttpURLConnection conexao = null;
            try {
                URL url = new URL(item.getImagemUrl());
                conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestMethod("GET");
                conexao.setDoInput(true);
                conexao.connect();

                InputStream is = conexao.getInputStream();
                return ( BitmapFactory.decodeStream(is) );

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(conexao != null){
                    conexao.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if(result != null && item != null) {
                item.getImageView().setImageBitmap(result);
            }
            super.onPostExecute(result);
        }
    }*/
}
