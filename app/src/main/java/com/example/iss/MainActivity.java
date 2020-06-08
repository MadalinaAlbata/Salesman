package com.example.iss;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
AdapterProdus adapter;
DataBaseHelper db;
    ArrayList<Produs> produs;
    Button add;
    Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        add=(Button)findViewById(R.id.add_product);
        display=(Button) findViewById(R.id.display_catalog);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCatalog();
            }
        });

    }

    private void addProduct() {
        Intent intent=new Intent(this,AdaugaProdus.class);
        startActivity(intent);
    }
    private void displayCatalog(){
        Intent intent=new Intent(this,DisplayCatalog_PtAdmin.class);
        startActivity(intent);
    }


}
