package dades.excepcions;

/**
 * Excepció per activitat duplicada
 */
public class ActivitatDuplicadaException extends Exception {
    public ActivitatDuplicadaException(String missatge) {
        super(missatge);
    }
}
