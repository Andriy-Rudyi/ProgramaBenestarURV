package dades.excepcions;

/**
 * Excepció llançada quan s'intenta crear un usuari amb un col·lectiu invàlid
 * Els col·lectius vàlids són: PDI, PTGAS i Estudiants
 * 
 * @author Sistema de Gestió d'Usuaris URV
 */
public class ColectiuInvalidException extends Exception {
    
    /**
     * Constructor amb missatge per defecte
     */
    public ColectiuInvalidException() {
        super("El col·lectiu especificat no és vàlid");
    }
    
    /**
     * Constructor amb missatge personalitzat
     * @param missatge Missatge descriptiu de l'error
     */
    public ColectiuInvalidException(String missatge) {
        super(missatge);
    }
    
    /**
     * Constructor amb missatge i causa
     * @param missatge Missatge descriptiu de l'error
     * @param causa Excepció que va causar aquest error
     */
    public ColectiuInvalidException(String missatge, Throwable causa) {
        super(missatge, causa);
    }
}