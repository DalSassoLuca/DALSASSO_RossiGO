package org.example;

public class Noleggio {

    private Utente utente;
    private Veicolo veicolo;
    private java.time.LocalDateTime orarioInizio;
    private java.time.LocalDateTime orarioFine;

    public Noleggio(Utente utente, Veicolo veicolo, java.time.LocalDateTime orarioInizio, java.time.LocalDateTime orarioFine) {
        this.utente = utente;
        this.veicolo = veicolo;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
    }

    // Getters and setters
    public Utente getUtente() {
        return utente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public java.time.LocalDateTime getOrarioInizio() {
        return orarioInizio;
    }

    public java.time.LocalDateTime getOrarioFine() {
        return orarioFine;
    }
}
