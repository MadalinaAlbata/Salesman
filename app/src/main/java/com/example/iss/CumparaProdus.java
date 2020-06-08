package com.example.iss;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CumparaProdus extends AppCompatActivity {
TextView titlu;
TextView pret;
EditText cantitate;
Button buy,cancel;
DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumpara_produs);
        Intent intent=getIntent();

        db=new DataBaseHelper(this);
        titlu=(TextView)findViewById(R.id.titlu);
        String set_titlu=intent.getStringExtra("nume_produs");
        titlu.setText(set_titlu);

        pret=(TextView)findViewById(R.id.pret);
        cantitate=(EditText)findViewById(R.id.cantitate);
        cantitate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               int p=setPret();
            /*   int cantitate_adaugata=0;
                Cursor cursor=db.getNume(titlu.getText().toString());
                while (cursor.moveToNext()) {
                    String cantitate1 = cursor.getString(3);
                    cantitate_adaugata=Integer.parseInt(cantitate1);
                }
                if(Integer.parseInt(cantitate.getText().toString())<=cantitate_adaugata)*/
                pret.setText(p+"");
             //   else cantitate.setError("This quantity is not avaible");
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        buy=(Button)findViewById(R.id.button_completeOrder);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy();}

        });
        cancel=(Button)findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void buy(){
        if (cantitate.length()==0)
            cantitate.setError("Select Quantity");
        else{
        Cursor cursor=db.getNume(titlu.getText().toString());
        int cant_noua=0;
        String ca=cantitate.getText().toString();
        while(cursor.moveToNext()){
            String ca1=cursor.getString(3);
            cant_noua=Integer.parseInt(ca1)-Integer.parseInt(ca);
        }
        if(cant_noua>=0){
            Toast.makeText(CumparaProdus.this, "Your oreder has been succesfully completed", Toast.LENGTH_LONG).show();
            boolean update=db.updateCantitate(titlu.getText().toString(), pret.getText().toString(),cant_noua+"" );
            if(update==true)
                Toast.makeText(CumparaProdus.this, "", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(CumparaProdus.this, "", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(CumparaProdus.this,DisplayCatalog.class);
            intent.putExtra("cantitate",cant_noua+"");
            startActivity(intent);
            finish();
        }
        else
            cantitate.setError("This quantity is not avaible");
    }}

    private int setPret(){
        Cursor cursor=db.getNume(titlu.getText().toString());
        int pret_nou=1;
        while(cursor.moveToNext()){
            String pret=cursor.getString(2);
            pret_nou=Integer.parseInt(pret)*Integer.parseInt(cantitate.getText().toString());
        }
        return pret_nou;
    }
}
