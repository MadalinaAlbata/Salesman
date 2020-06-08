package com.example.iss;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends AppCompatActivity {
EditText c;
Button b;
DataBaseHelper db;
String nume,pret,cant,descr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        db=new DataBaseHelper(this);
        final Intent intent=getIntent();
        nume=intent.getStringExtra("n");
        pret=intent.getStringExtra("p");
        cant=intent.getStringExtra("c");
        descr=intent.getStringExtra("d");
        c=(EditText)findViewById(R.id.delete_c);
        b=(Button)findViewById(R.id.button_delete_c);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cant_noua=0;
                String ca=c.getText().toString();
                Cursor cursor=db.getNume(nume);

                while(cursor.moveToNext()){
                    String ca1=cursor.getString(3);
                    cant_noua=Integer.parseInt(ca1)-Integer.parseInt(ca);
                }
                if(cant_noua>0){
                boolean update=db.updateCantitate(nume, pret,cant_noua+"" );
                if(update==true)
                    Toast.makeText(delete.this, "data deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(delete.this, "data not deleted", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(delete.this,DisplayCatalog_PtAdmin.class);
                startActivity(intent);
                }
                else if(cant_noua==0){
                        String id=db.getId(nume,pret,cant,descr);
                        Integer delete=db.deleteData(id);
                        if(delete>0)
                            Toast.makeText(delete.this, " data deleted", Toast.LENGTH_LONG).show();
                        else                    Toast.makeText(delete.this, "   data not deleted", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(delete.this,DisplayCatalog_PtAdmin.class);
                        startActivity(intent);
                        finish();
                }
                    else
                    c.setError("Incorect number");
            }
        });
    }

}
