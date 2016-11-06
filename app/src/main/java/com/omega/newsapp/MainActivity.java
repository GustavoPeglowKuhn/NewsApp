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

        /*List<Item> itemsList = new ArrayList<>();

        itemsList.add(new Item(null, "001", "ipsum dolor sit amet, ad mei nullam graece alienum, vix fabellas" +
                "vulputate ei. Meis luptatum singulis est ea. Pri id quot labore, suas" +
                "melius quaeque cu eam. In suas aliquip pro. Eos ad everti percipitur" +
                "interesset, fuisset consequuntur ut sea, labore vivendo eos te. No vel" +
                "putent placerat, vel in malis deserunt.", "30/02/2017"));
        itemsList.add(new Item(null, "Titulo", "t eos solet detracto temporibus, eu docendi apeirian\n" +
                "euripidis vim, eam atqui option id. Possim aperiam cum ne, qui ut ullum\n" +
                "delenit singulis. Omnis essent instructior ex mel, sea ad esse sale\n" +
                "partem, simul melius docendi ne sed. Eam te aperiri omittam\n" +
                "interpretaris.", "30/02/2018"));
        itemsList.add(new Item(null, "Lorem Ipsum", "lorem Ipsum", "30/02/2019"));
        itemsList.add(new Item(null, "Birl", "lorem Ipsum", "30/02/2020"));*/

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
            try {
                //https://drive.google.com/file/d/0Bz8_rhlT4pQxQnJiSElOSFE0Y0k/view?usp=sharing
                //https://drive.google.com/open?id=0Bz8_rhlT4pQxQnJiSElOSFE0Y0k

                URL url = new URL("http://alemao.esy.es/news.json");
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

                return JsonUtil.fromJson(buffer.toString());
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
            return null;
        }

        @Override
        protected void onPostExecute(List<Item> itens) {
            adapter.clear();
            adapter.addAll(itens);
            adapter.notifyDataSetChanged();
        }
    }

}
