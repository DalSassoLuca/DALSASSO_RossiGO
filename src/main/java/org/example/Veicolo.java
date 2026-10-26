package org.example;

public abstract class Veicolo {
    private int Codice;
    private double Percentuale;
    private String Stato;

    public Veicolo(int codice, double percentuale, String stato) {
        this.Codice = codice;
        this.Percentuale = percentuale;
        this.Stato = stato;
    }

    public int getCodice() {
        return Codice;
    }

    public double getPercentuale() {
        return Percentuale;
    }

    public String getStato() {
        return Stato;
    }

    public void setPercentuale(double percentuale) {
        Percentuale = percentuale;
    }

    public void setStato(String stato) {
        Stato = stato;

    }

    public abstract double calcolaCosto(int minuti);

    public abstract double consumoBatteriaPerMinuto();

}
