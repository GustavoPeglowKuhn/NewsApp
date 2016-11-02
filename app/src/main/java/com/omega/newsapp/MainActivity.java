package com.omega.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lista = (ListView) findViewById(R.id.lista);

        List<Item> itemsList = new ArrayList<>();

        itemsList.add(new Item(null, "001", "ipsum dolor sit amet, ad mei nullam graece alienum, vix fabellas\n" +
                "vulputate ei. Meis luptatum singulis est ea. Pri id quot labore, suas\n" +
                "melius quaeque cu eam. In suas aliquip pro. Eos ad everti percipitur\n" +
                "interesset, fuisset consequuntur ut sea, labore vivendo eos te. No vel\n" +
                "putent placerat, vel in malis deserunt.", "30/02/2017"));
        itemsList.add(new Item(null, "Titulo", "t eos solet detracto temporibus, eu docendi apeirian\n" +
                "euripidis vim, eam atqui option id. Possim aperiam cum ne, qui ut ullum\n" +
                "delenit singulis. Omnis essent instructior ex mel, sea ad esse sale\n" +
                "partem, simul melius docendi ne sed. Eam te aperiri omittam\n" +
                "interpretaris.", "30/02/2018"));
        itemsList.add(new Item(null, "Lorem Ipsum", "lorem Ipsum", "30/02/2019"));
        itemsList.add(new Item(null, "Birl", "lorem Ipsum", "30/02/2020"));

        adapter = new ItemAdapter(this, itemsList);
        lista.setAdapter(adapter);
    }


}
