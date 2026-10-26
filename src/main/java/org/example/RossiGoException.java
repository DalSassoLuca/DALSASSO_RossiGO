package org.example;

public class RossiGoException extends Exception {
    public RossiGoException(String message) {
        super(message);
    }

    public RossiGoException(String message, Throwable cause) {
        super(message, cause);
    }



    public static class VeicoloNonDisponibileException extends RossiGoException {
        public VeicoloNonDisponibileException(String message) {
            super(message);
        }
    }

    public static class BatteriaScaricaException extends RossiGoException {
        public BatteriaScaricaException(String message) {
            super(message);
        }
        public BatteriaScaricaException(String message, Throwable cause) {
            super(message, cause);
        }


    }

    public static class CreditoInsufficienteException extends RossiGoException {
        public CreditoInsufficienteException(String message) {
            super(message);
        }
    }

    public static class UtenteNonTrovatoException extends RossiGoException {
        public UtenteNonTrovatoException(String message) {
            super(message);
        }
    }

    public static class CodiceFiscaleNonValidoException extends RossiGoException {
        public CodiceFiscaleNonValidoException(String message) {
            super(message);
        }
    }
}
