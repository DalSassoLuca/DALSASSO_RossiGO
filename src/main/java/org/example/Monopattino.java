package org.example;

public class Monopattino extends Veicolo {
    private int VelocitaMassima;
    public Monopattino(int codice, double percentuale, String stato, int velocitaMassima) {
        super(codice, percentuale, stato);
        this.VelocitaMassima = velocitaMassima;
    }

    @Override
    public double calcolaCosto(int minuti) {
        // Implementazione specifica per il monopattino
        return minuti * 0.5; // Costo per minuto per il monopattino
    }

    @Override
    public double consumoBatteriaPerMinuto() {
        // Implementazione specifica per il monopattino
        return 2.0; // Consumo di batteria per minuto per il monopattino
    }

    public boolean isLimitatoInZonaPedonale() {
        // Implementazione specifica per il monopattino
        return false; // Esempio di implementazione, da modificare secondo le specifiche
    }
}
