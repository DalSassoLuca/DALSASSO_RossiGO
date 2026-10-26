
package org.example;

import java.util.ArrayList;
import java.util.List;
import org.example.RossiGoException.CodiceFiscaleNonValidoException;

public class FlottaService {
    private List<Veicolo> veicoli;
    private List<Utente> utenti;
    private List<Noleggio> noleggi;

    public FlottaService() {
        this.veicoli = new ArrayList<>();
        this.utenti = new ArrayList<>();
        this.noleggi = new ArrayList<>();
    }

    public List<Veicolo> getVeicoli() {
        return veicoli;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public List<Noleggio> getNoleggi() {
        return noleggi;
    }

    public void registraVeicolo(Veicolo v) {
        veicoli.add(v);
    }

    public void registraUtente(Utente u)
            throws CodiceFiscaleNonValidoException {

        if (!u.isCodiceFiscaleValido()) {
            throw new CodiceFiscaleNonValidoException(
                    "Codice fiscale non valido"
            );
        }

        utenti.add(u);
    }

    public void sbloccaVeicolo(String codice, String idUtente)
            throws RossiGoException.VeicoloNonDisponibileException,
            RossiGoException.UtenteNonTrovatoException {

        Veicolo veicolo = veicoli.stream()
                .filter(v -> v.getCodice() == Integer.parseInt(codice))
                .findFirst()
                .orElseThrow(() -> new RossiGoException.VeicoloNonDisponibileException(
                        "Veicolo non disponibile"
                ));

        Utente utente = utenti.stream()
                .filter(u -> u.getId() == Integer.parseInt(idUtente))
                .findFirst()
                .orElseThrow(() -> new RossiGoException.UtenteNonTrovatoException(
                        "Utente non trovato"
                ));

        if (!veicolo.getStato().equals("IN_RICARICA") && !veicolo.getStato().equals("IN_USO")) {
            throw new RossiGoException.VeicoloNonDisponibileException(
                    "Veicolo non disponibile"
            );
        }

        veicolo.setStato("IN_USO");
    }

    public void terminaNoleggio(String codice, int minutiUsati)
            throws RossiGoException.VeicoloNonDisponibileException,
            RossiGoException.BatteriaScaricaException,
            RossiGoException.CreditoInsufficienteException {

        Veicolo veicolo = veicoli.stream()
                .filter(v -> v.getCodice() == Integer.parseInt(codice))
                .findFirst()
                .orElseThrow(() -> new RossiGoException.VeicoloNonDisponibileException(
                        "Veicolo non disponibile"
                ));

        if (!veicolo.getStato().equals("IN_USO")) {
            throw new RossiGoException.VeicoloNonDisponibileException(
                    "Veicolo non in uso"
            );
        }

        double consumoBatteria = veicolo.consumoBatteriaPerMinuto() * minutiUsati;
        if (veicolo.getPercentuale() - consumoBatteria < 0) {
            throw new RossiGoException.BatteriaScaricaException(
                    "Batteria scarica"
            );
        }

        double costoNoleggio = veicolo.calcolaCosto(minutiUsati);
        Utente utente = null;
        try {
            utente = utenti.stream()
                    .filter(u -> u.getId() == Integer.parseInt(codice))
                    .findFirst()
                    .orElseThrow(() -> new RossiGoException.UtenteNonTrovatoException(
                            "Utente non trovato"
                    ));
        } catch (RossiGoException.UtenteNonTrovatoException e) {
            throw new RuntimeException(e);
        }

        if (utente.getCredito() < costoNoleggio) {
            throw new RossiGoException.CreditoInsufficienteException(
                    "Credito insufficiente"
            );
        }

        utente.setCredito(utente.getCredito() - costoNoleggio - 1.0); // Sottrai il costo del noleggio e la penale di 1 euro
        veicolo.setPercentuale(veicolo.getPercentuale() - consumoBatteria);
        veicolo.setStato("DISPONIBILE");
    }

    public void mettiInRicarica(String codice)
            throws RossiGoException.VeicoloNonDisponibileException {

        Veicolo veicolo = veicoli.stream()
                .filter(v -> v.getCodice() == Integer.parseInt(codice))
                .findFirst()
                .orElseThrow(() -> new RossiGoException.VeicoloNonDisponibileException(
                        "Veicolo non disponibile"
                ));

        if (!veicolo.getStato().equals("DISPONIBILE")) {
            throw new RossiGoException.VeicoloNonDisponibileException(
                    "Veicolo non disponibile"
            );
        }

        veicolo.setStato("IN_RICARICA");
    }
}
