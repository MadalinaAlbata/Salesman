package com.example.iss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayCatalog_PtAdmin extends AppCompatActivity {
    ListView listView;
    AdapterProdus_Admin adapter;
    DataBaseHelper db;
    ArrayList<Produs> produs;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_catalog__pt_admin);
        db=new DataBaseHelper(this);
        add=(Button)findViewById(R.id.addd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        listView=(ListView)findViewById(R.id.listViewCatalog1);
        adapter=new AdapterProdus_Admin(this,this.getProdus());
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),DetaliiProdus_Admin.class);
                intent.putExtra("nume",produs.get(position).getNume());
                intent.putExtra("pret",produs.get(position).getPret()+"");
                intent.putExtra("cantitate",produs.get(position).getCant()+"");
                intent.putExtra("descriere",produs.get(position).getDescriere());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Produs> getProdus() {
        produs =new ArrayList<>();
        produs=db.getAllProduse();
        return produs;
    }

    private void addProduct() {
        Intent intent=new Intent(this,AdaugaProdus.class);
        startActivity(intent);
    }
}
