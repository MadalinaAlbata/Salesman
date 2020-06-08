package com.example.iss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class AdapterProdus extends BaseAdapter {
    Context mcontext;
    private LayoutInflater minflater;
    ArrayList<Produs> produs;

    public AdapterProdus(Context mcontext, ArrayList<Produs> produs) {
        this.mcontext = mcontext;
        this.produs = produs;
        minflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return produs.size();
    }

    @Override
    public Object getItem(int position) {
        return produs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = minflater.inflate(R.layout.produs, null);
        Produs prod = produs.get(position);
        if (prod != null) {
            TextView nume = (TextView) convertView.findViewById(R.id.nume_produs);
            TextView pret = (TextView) convertView.findViewById(R.id.pret_produs);
            TextView cantitate = (TextView) convertView.findViewById(R.id.cant_produs);
            if (nume != null) {
                nume.setText(prod.getNume());
                pret.setText(String.valueOf(prod.getPret()));
                cantitate.setText(String.valueOf(prod.getCant()));
            }
            if (prod.getCant()== 0) {
                convertView.setBackgroundResource(R.color.colorAccent);
            }
        }

        return convertView;
    }

    public boolean isEnabled(int position) {
        if(produs.get(position).getCant()==0)
        return false;
        else return true;
    }
}