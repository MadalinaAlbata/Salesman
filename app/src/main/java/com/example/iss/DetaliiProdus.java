package com.example.iss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetaliiProdus extends AppCompatActivity {
TextView nume,pret,descriere,cantitate;
Button button_buy;
String get_nume,get_pret,get_cantitate,get_descriere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_produs);
        Intent intent=getIntent();
        nume=(TextView)findViewById(R.id.detalii_nume);
        get_nume=intent.getStringExtra("nume");
        nume.setText(get_nume);
        pret=(TextView)findViewById(R.id.detalii_pret);
        get_pret=intent.getStringExtra("pret");
        pret.setText(get_pret);
        descriere=(TextView)findViewById(R.id.detalii_descriere);
        get_descriere=intent.getStringExtra("descriere");
        descriere.setText(get_descriere);
        cantitate=(TextView) findViewById(R.id.detalii_cantitate);
        get_cantitate=intent.getStringExtra("cantitate");


        cantitate.setText(get_cantitate);
        button_buy=(Button)findViewById(R.id.buy_button);
        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        Intent intent =new Intent(this,CumparaProdus.class);
        intent.putExtra("nume_produs",get_nume);
        startActivity(intent);
    }
}
