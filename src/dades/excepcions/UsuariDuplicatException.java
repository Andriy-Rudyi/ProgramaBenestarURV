package dades.excepcions;

/**
 * Excepció per usuari duplicat
 */
public class UsuariDuplicatException extends Exception {
    public UsuariDuplicatException(String missatge) {
        super(missatge);
    }
}
