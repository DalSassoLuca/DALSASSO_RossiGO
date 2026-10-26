
package org.example;

import org.example.RossiGoException.*;
import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

import static java.lang.IO.println;
import static java.lang.IO.readln;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println(FigletFont.convertOneLine("RossiGo"));
        System.out.println("Noleggio di bici e monopattini\n");

        FlottaService flotta = new FlottaService();

        // 1. Registrazione degli utenti
        Utente u1 = new Utente(
                1, "Luca Dal Sasso", "luca@email.it",
                "DLSLCU08P03L840Q", 20.00
        );

        Utente u2 = new Utente(
                2, "Mario Bianchi", "luca@email.it",
                "BNCLCU90A01H501U", 0.50
        );

        try {
            flotta.registraUtente(u1);
            System.out.println("Utente Mario registrato correttamente.");
        } catch (RossiGoException e) {
            System.out.println("Errore registrazione: " + e.getMessage());
        }

        try {
            flotta.registraUtente(u2);
            System.out.println("Utente Luca registrato correttamente.");
        } catch (RossiGoException e) {
            System.out.println("Errore registrazione: " + e.getMessage());
        }

        // 2. Registrazione dei veicoli
        Veicolo bici = new Bici(0001, 100, "LIBERO", 7);
        Veicolo monopattino = new Monopattino(0002, 100, "LIBERO", 25);
        Veicolo biciScarica = new Bici(0003, 10, "OCCUPATO", 7);

        flotta.registraVeicolo(bici);
        flotta.registraVeicolo(monopattino);
        flotta.registraVeicolo(biciScarica);

        System.out.println("\nVeicoli registrati correttamente.");

        // SCENARIO 1: noleggio di una bici
        System.out.println("\n--- Scenario 1: noleggio bici ---");
        try {
            flotta.sbloccaVeicolo("0001", "1");
            flotta.terminaNoleggio("0001", 10);
            System.out.println("Noleggio della bici terminato.");
        } catch (RossiGoException e) {
            System.out.println("Impossibile completare il noleggio: "
                    + e.getMessage());
        }

        // SCENARIO 2: credito insufficiente
        System.out.println("\n--- Scenario 2: credito insufficiente ---");
        try {
            flotta.sbloccaVeicolo("0002", "2");
        } catch (RossiGoException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // SCENARIO 3: batteria scarica
        System.out.println("\n--- Scenario 3: batteria scarica ---");
        try {
            flotta.sbloccaVeicolo("0003", "1");
        } catch (RossiGoException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // SCENARIO 4: veicolo già in uso
        System.out.println("\n--- Scenario 4: veicolo già in uso ---");
        try {
            flotta.sbloccaVeicolo("0001", "1");
        } catch (VeicoloNonDisponibileException e) {
            System.out.println("Il mezzo non è disponibile.");
        } catch (RossiGoException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // SCENARIO 5: utente inesistente
        System.out.println("\n--- Scenario 5: utente inesistente ---");
        try {
            flotta.sbloccaVeicolo("0002", "999");
        } catch (UtenteNonTrovatoException e) {
            System.out.println("Utente non trovato. Registrati prima di noleggiare.");
        } catch (RossiGoException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // SCENARIO 6: codice fiscale non valido
        System.out.println("\n--- Scenario 6: codice fiscale non valido ---");
        Utente utenteErrato = new Utente(
                3, "Utente Test", "test@email.it",
                "CODICEFISCALEERRATO", 10.00
        );

        try {
            flotta.registraUtente(utenteErrato);
        } catch (CodiceFiscaleNonValidoException e) {
            System.out.println("Registrazione rifiutata: codice fiscale non valido.");
        } catch (RossiGoException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // SCENARIO 7: mettere un mezzo in ricarica
        System.out.println("\n--- Scenario 7: ricarica ---");
        try {
            flotta.mettiInRicarica("0003");
            System.out.println("Il mezzo è stato messo in ricarica.");
        } catch (RossiGoException e) {
            System.out.println("Impossibile avviare la ricarica: "
                    + e.getMessage());
        }

        Utente n = new Utente(0005, readln("Inserisci il tuo nome: "),
                readln("Inserisci la tua email: "),
                readln("Inserisci il tuo codice fiscale: "),
                Double.parseDouble(readln("Inserisci il tuo credito: "))
        );
        println("Benvenuto " + n.getNome() + "! Il tuo credito è: " + n.getCredito() + "€");
    }
}
