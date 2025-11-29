package dades.excepcions;

/**
 * Excepció per quan no hi ha places disponibles
 */
class NoHiHaPlacesException extends Exception {
    public NoHiHaPlacesException(String missatge) {
        super(missatge);
    }
}
