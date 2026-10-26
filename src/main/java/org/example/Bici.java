package org.example;

public class Bici extends Veicolo {
    private int Marce;

    public Bici(int codice, double percentuale, String stato, int marce) {
        super(codice, percentuale, stato);
        this.Marce = marce;
    }

    @Override
    public double calcolaCosto(int minuti) {
        // Implementazione specifica per la bici
        return minuti * 0.15; // Costo per minuto per la bici
    }

    @Override
    public double consumoBatteriaPerMinuto() {
        // Implementazione specifica per la bici
        return 1.0; // Consumo di batteria per minuto per la bici
    }

    public int getNumeroMarce() {
        return Marce;
    }
}
