package dades.excepcions;

/**
 * Excepció que indica la cancel·lació de la introducció d'entrada per permetre
 * el retorn al menú principal.
 */
public class CancelarOperacioException extends RuntimeException {
    public CancelarOperacioException() {
        super();
    }
}
