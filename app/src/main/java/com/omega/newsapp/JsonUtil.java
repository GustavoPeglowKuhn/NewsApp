package com.omega.newsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omega on 2016-11-05.
 */

public class JsonUtil {
    public static List<Item> fromJson(String json) throws JSONException {

        List<Item> lista = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            String imagem = (String) object.get("imagem");
            String titulo = (String) object.get("titulo");
            String resumo = (String) object.get("resumo");
            String data = (String) object.get("data");

            lista.add(new Item(imagem, titulo, resumo, data));
        }

        return lista;
    }
}
