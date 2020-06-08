package com.example.iss;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdaugaProdus extends AppCompatActivity {
    EditText nume,pret,cantitate,descriere;
    DataBaseHelper db;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_produs);
        nume=(EditText)findViewById(R.id.add_nume);
        descriere=(EditText) findViewById(R.id.add_descriere);
        pret=(EditText)findViewById(R.id.add_price);
        cantitate=(EditText)findViewById(R.id.add_cantitate);
        add=(Button)findViewById(R.id.adauga_produs);
        db=new DataBaseHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaugaProdus();
           //     finish();
            }
        });
    }

    private void adaugaProdus() {
        if(nume.length()==0){
            nume.setError("Set Name");
        }
        else if(pret.length()==0){
            pret.setError("Set Price");
        }else if(cantitate.length()==0){
            cantitate.setError("Set Quantity");
        }else if(descriere.length()==0)
        descriere.setError("Set Description");
        else{
            Cursor cursor=db.getNume(nume.getText().toString());
            int lungime=cursor.getCount();
            if(lungime==0) {
                boolean isInserted = db.insertProdus(nume.getText().toString(), pret.getText().toString(), cantitate.getText().toString(),descriere.getText().toString());
                if (isInserted == true)
                    Toast.makeText(AdaugaProdus.this, "data inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AdaugaProdus.this, "data not inserted", Toast.LENGTH_LONG).show();
                Intent intent =new Intent(this,DisplayCatalog_PtAdmin.class);
                finish();

            startActivity(intent);
            }
            else { int cant_noua=0;
              String ca=cantitate.getText().toString();
              while(cursor.moveToNext()){
              String ca1=cursor.getString(3);
               cant_noua=Integer.parseInt(ca)+Integer.parseInt(ca1);
              }
                boolean update=db.updateCantitate(nume.getText().toString(), pret.getText().toString(),cant_noua+"" );
                if(update==true)
                    Toast.makeText(AdaugaProdus.this, "data updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AdaugaProdus.this, "data not updated", Toast.LENGTH_LONG).show();
                Intent intent =new Intent(this,DisplayCatalog_PtAdmin.class);
                finish();

                startActivity(intent);
            }
        }

    }
}
