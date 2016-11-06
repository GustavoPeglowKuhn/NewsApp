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

        ImageView imagem = (ImageView) itemView.findViewById(R.id.item_imagem);
        imagem.setImageBitmap(item.getImagem());
        //imagem.setImageDrawable();    ??      see:    http://www.devmedia.com.br/utilizando-imageview-no-android/27783
        //imagem.setImageURI();         ??


        TextView titulo = (TextView) itemView.findViewById(R.id.item_titulo);
        titulo.setText(item.getTitulo());

        TextView resumo = (TextView) itemView.findViewById(R.id.item_resumo);
        resumo.setText(item.getResumo());

        TextView data = (TextView) itemView.findViewById(R.id.item_data);
        data.setText(item.getData());

        return itemView;
    }
}
