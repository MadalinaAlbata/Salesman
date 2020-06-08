package com.example.iss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayCatalog extends AppCompatActivity {
    ListView listView;
    AdapterProdus adapter;
    DataBaseHelper db;
    ArrayList<Produs> produs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_produs);
        db=new DataBaseHelper(this);
        listView=(ListView)findViewById(R.id.listViewCatalog);
        adapter=new AdapterProdus(this,this.getProdus());
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),DetaliiProdus.class);
                intent.putExtra("nume",produs.get(position).getNume());
                intent.putExtra("pret",produs.get(position).getPret()+"");
                intent.putExtra("cantitate",produs.get(position).getCant()+"");
                intent.putExtra("descriere",produs.get(position).getDescriere());
                startActivity(intent);
            }
        });
    }

    private void clickItem() {

    }

    private ArrayList<Produs> getProdus() {
        produs =new ArrayList<>();
        produs=db.getAllProduse();
        return produs;
    }
}
