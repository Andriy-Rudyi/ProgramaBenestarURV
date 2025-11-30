package dades.excepcions;

/**
 * Excepció per data incorrecta
 */
public class DataIncorrectaExcepction extends Exception{
    public DataIncorrectaExcepction(int dia, int mes, int any){
        super(dia + " " + mes + " " + any + " no és una data correcta");
    }
}
