package com.example.iss;

class Produs {
    String nume;
    float pret;
    int cant;
    String descriere;

    public Produs(String nume, float pret, int cant,String descriere) {
        this.nume = nume;
        this.pret = pret;
        this.cant = cant;
        this.descriere=descriere;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
