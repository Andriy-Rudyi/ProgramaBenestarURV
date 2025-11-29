package dades.usuaris;

/**
 * Classe Pare Usuari
 * @author PROG4 - Tiago Amarelle Rodrigues
 */

public abstract class Usuari {
    protected String alies;
    protected String adreca;

    public String getAlies(){
        return alies;
    }

    public String getAdreca(){
        return adreca;
    }

    public abstract String toString();

    public abstract Usuari copia();
}
