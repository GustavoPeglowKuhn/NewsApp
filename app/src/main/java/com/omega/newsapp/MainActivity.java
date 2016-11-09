package com.omega.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView lista = (ListView) findViewById(R.id.lista);

        adapter = new ItemAdapter(this, new ArrayList<Item>());

        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
                ItemVideo aula = (ItemVideo) parent.getItemAtPosition(position);
                intent.putExtra("AULA", aula);
                startActivity(intent);*/
            }
        });

        new NewsTask().execute();
    }

    class NewsTask extends AsyncTask<Void, Void, List<Item>> {

        @Override
        protected List<Item> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            List<Item> lista = null;

            String s = getResources().getString(R.string.json_link);
            try {
                URL url = new URL(s);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String linha;
                StringBuffer buffer = new StringBuffer();
                while((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                    buffer.append("\n");
                }

                lista = JsonUtil.fromJson(buffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return lista;
        }

        @Override
        protected void onPostExecute(List<Item> itens) {
            if(itens != null) {
                adapter.clear();
                adapter.addAll(itens);
                adapter.notifyDataSetChanged();
            }else {
                adapter.add(new Item(null, "erro", "erro de conecção, não adianta chingar o programador, tua internet que é uma bosta", "09/11/2016"));
                adapter.notifyDataSetChanged();
            }
            super.onPostExecute(itens);
        }
    }

}
