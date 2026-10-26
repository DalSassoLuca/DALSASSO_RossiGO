
package org.example;

import it.kamaladafrica.codicefiscale.CodiceFiscale;

public class Utente {
    private int id;
    private String nome;
    private String email;
    private String codiceFiscale;
    private double credito;

    public Utente(int id, String nome, String email,
                  String codiceFiscale, double credito) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.credito = credito;
    }

    public int getId() {
        return id;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public boolean isCodiceFiscaleValido() {
        return CodiceFiscale.isFormatValid(codiceFiscale);
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }
}
