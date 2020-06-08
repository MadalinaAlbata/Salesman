package com.example.iss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetaliiProdus_Admin extends AppCompatActivity {
    EditText pret,descriere,cantitate,id;
    TextView nume;
    Button button_update,button_delete;
    String get_nume,get_pret,get_cantitate,get_descriere;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_produs__admin);
        db=new DataBaseHelper(this);
        Intent intent=getIntent();
        nume=(TextView) findViewById(R.id.detalii_nume1);
        get_nume=intent.getStringExtra("nume");
        nume.setText(get_nume);
        pret=(EditText) findViewById(R.id.detalii_pret1);
        get_pret=intent.getStringExtra("pret");
        pret.setText(get_pret);
        descriere=(EditText) findViewById(R.id.detalii_descriere1);
        get_descriere=intent.getStringExtra("descriere");
        descriere.setText(get_descriere);
        cantitate=(EditText) findViewById(R.id.detalii_cantitate1);
        get_cantitate=intent.getStringExtra("cantitate");
        cantitate.setText(get_cantitate);

        button_delete=(Button)findViewById(R.id.delete_button);
        button_update=(Button)findViewById(R.id.update_button);
        
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(DetaliiProdus_Admin.this,delete.class);
              inten.putExtra("n",nume.getText().toString());
              inten.putExtra("p",pret.getText().toString());
              inten.putExtra("c",cantitate.getText().toString());
              inten.putExtra("d",descriere.getText().toString());

                startActivity(inten);
            //    delete();
             //   finish();
            }
        });
        
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                finish();
            }
        });
    }

    private void update() {
      //  String id=db.getId(nume.getText().toString(),pret.getText().toString(),cantitate.getText().toString(),descriere.getText().toString());
        boolean update=db.updateData(nume.getText().toString(),pret.getText().toString(),cantitate.getText().toString(),descriere.getText().toString());
        if(update==true)
            Toast.makeText(DetaliiProdus_Admin.this, " EDIT", Toast.LENGTH_LONG).show();
        else                    Toast.makeText(DetaliiProdus_Admin.this, "  NOT EDIT", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,DisplayCatalog_PtAdmin.class);
        startActivity(intent);
    }

    private void delete() {
        String id=db.getId(nume.getText().toString(),pret.getText().toString(),cantitate.getText().toString(),descriere.getText().toString());
        Integer delete=db.deleteData(id);
        if(delete>0)
            Toast.makeText(DetaliiProdus_Admin.this, " data deleted", Toast.LENGTH_LONG).show();
        else                    Toast.makeText(DetaliiProdus_Admin.this, "   data not deleted", Toast.LENGTH_LONG).show();

    }



}
