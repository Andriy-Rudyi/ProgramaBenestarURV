package dades.usuaris;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import dades.excepcions.UsuariDuplicatException;

/**
 * Llista d'usuaris ordenada per alias per facilitar les cerques
 * @author PROG1 - Pau Font
 */
public class LlistaUsuaris implements Serializable{
    private static final long serialVersionUID = 1L;
    private Usuari[] llista;
    private int numUsuaris;
    private static final int MIDA_INICIAL = 50;
    
    /**
     * Constructor de LlistaUsuaris
     */
    public LlistaUsuaris() {
        llista = new Usuari[MIDA_INICIAL];
        numUsuaris = 0;
    }
    
    /**
     * Redimensiona l'array quan està ple
     */
    private void redimensionar() {
        Usuari[] novaLlista = new Usuari[llista.length * 2];
        for (int i = 0; i < numUsuaris; i++) {
            novaLlista[i] = llista[i];
        }
        llista = novaLlista;
    }
    
    /**
     * Afegeix un usuari a la llista mantenint l'ordre alfabètic per alias
     * @param usuari Usuari a afegir
     * @throws UsuariDuplicatException si l'usuari ja existeix
     */
    public boolean afegir(Usuari usuari) throws UsuariDuplicatException {
        // null check
        if (usuari == null) {
            return false;
        }
        
        // Comprovar si ja existeix
        if (buscar(usuari.getAlies()) != null) {
            throw new UsuariDuplicatException("L'usuari amb alias " + usuari.getAlies() + " ja existeix");
        }
        
        // Redimensionar si cal
        if (numUsuaris == llista.length) {
            redimensionar();
        }
        
        // Trobar posició d'inserció mantenint ordre alfabètic
        int pos = 0;
        while (pos < numUsuaris && llista[pos].getAlies().compareTo(usuari.getAlies()) < 0) {
            pos++;
        }
        
        // Desplaçar elements cap a la dreta
        for (int i = numUsuaris; i > pos; i--) {
            llista[i] = llista[i - 1];
        }
        
        // Inserir l'usuari
        llista[pos] = usuari.copia();
        numUsuaris++;
        return true;
    }
    
    /**
     * Busca un usuari per alias (cerca binària aprofitant l'ordenació)
     * @param alies Alias a buscar
     * @return Usuari trobat o null si no existeix
     */
    public Usuari buscar(String alies) {
        // null check
        if (alies == null) {
            return null;
        }

        int esquerra = 0;
        int dreta = numUsuaris - 1;
        
        while (esquerra <= dreta) {
            int mig = (esquerra + dreta) / 2;
            int comparacio = llista[mig].getAlies().compareTo(alies);
            
            if (comparacio == 0) {
                return llista[mig];
            } else if (comparacio < 0) {
                esquerra = mig + 1;
            } else {
                dreta = mig - 1;
            }
        }
        return null;
    }
        
    /**
     * Obté tots els usuaris
     * @return Array d'usuaris
     */
    public Usuari[] obtenirTots() {
        Usuari[] resultat = new Usuari[numUsuaris];
        for (int i = 0; i < numUsuaris; i++) {
            resultat[i] = llista[i];
        }
        return resultat;
    }
    
    /**
     * Obté usuaris d'un col·lectiu concret
     * @param colectiu Col·lectiu a filtrar ("PDI", "PTGAS" o "Estudiants")
     * @return Array d'usuaris del col·lectiu especificat
     */
    public LlistaUsuaris obtenirPerColectiu(String colectiu) {
        if (colectiu == null) {
            return new LlistaUsuaris();
        }
    
        // crear array i omplir-lo
        LlistaUsuaris resultat = new LlistaUsuaris();
        for (int i = 0; i < numUsuaris; i++) {
            if (llista[i].getColectiu().equals(colectiu)) {
                try {
                    resultat.afegir(llista[i]);
                } catch (UsuariDuplicatException e) {
                    System.out.println("Error inesperat en obtenirPerColectiu. " + e);
                }
            }
        }

        return resultat;
    }

    /**
     * Elimina un usuari de la llista per àlies
     * @param alies Àlies de l'usuari a eliminar
     * @return true si s'ha eliminat, false si no existia
     */
    public boolean eliminar(String alies) {
        // null check
        if (alies == null) {
            return false;
        }
        
        // Buscar la posició de l'usuari
        int pos = -1;
        for (int i = 0; i < numUsuaris; i++) {
            if (llista[i].getAlies().equals(alies)) {
                pos = i;
                break;
            }
        }
        
        // Si no s'ha trobat, retornar false
        if (pos == -1) {
            return false;
        }
        
        // Desplaçar elements cap a l'esquerra
        for (int i = pos; i < numUsuaris - 1; i++) {
            llista[i] = llista[i + 1];
        }
        
        // Alliberar la darrera posició
        llista[numUsuaris - 1] = null;
        numUsuaris--;
        
        return true;
    }

    /**
     * Obté el nombre d'usuaris de la llista
     * @return Nombre d'usuaris
     */
    public int getNumUsuaris() { return numUsuaris; }
    
    public Usuari getUsuari(int index) {
        if (index < 0 || index >= numUsuaris) return null;
        return llista[index];
    }

    /**
     * Comprova si la llista està buida
     * @return true si la llista està buida, false en cas contrari
     */
    public boolean esBuida() { return numUsuaris == 0; }
    
    
    /**
     * Crea una còpia de la llista usuaris
     * @return Nova instància amb les mateixes dades
    */
   public LlistaUsuaris copia() {
       LlistaUsuaris copia = new LlistaUsuaris();
       
       for (int i = 0; i < numUsuaris; i++){
           try {
               copia.afegir(this.llista[i].copia());
            } catch (UsuariDuplicatException e) {
                System.out.println("Error inesperat copiant usuari. " + e);
            }
        }
        
        return copia;
    }

    public void carregarBaseDadesUsuaris(String nomFitxer) {
        try{ 
            Scanner f = new Scanner(new File(nomFitxer));
            f.useDelimiter(";");
            // LlistaUsuaris llistaUsuaris = new LlistaUsuaris();
            String[] linia;
            String tipusUsuari;
            while (f.hasNext()){
                try {
                    linia = f.nextLine().split(";");
                    tipusUsuari = linia[0];
                    if (tipusUsuari.equals(Usuari.COLECTIU_ESTUDIANTS)){
                        this.afegir(new Estudiant(linia[1], linia[2], linia[3], Integer.parseInt(linia[4])));
                    } else if (tipusUsuari.equals(Usuari.COLECTIU_PDI)){
                        this.afegir(new Pdi(linia[1], linia[2], linia[3], linia[4]));
                    } else if (tipusUsuari.equals(Usuari.COLECTIU_PTGAS)){
                        this.afegir(new Ptgas(linia[1], linia[2], linia[3]));
                    } else{
                        System.out.println("Error afegint usuari: No existeix el col·lectiu " + tipusUsuari);
                    }
                } catch (UsuariDuplicatException e) {
                    System.out.println("Usuari duplicat en fitxer. " + e.getMessage());
                }
            }
            f.close();
            // return this;
        } catch (IOException e) {
            System.out.println("Error llegint base de usuaris." + e);
            // return new LlistaUsuaris();
        }
    }

    public void guardarBaseDadesUsuaris(String nomFitxer) {
        try{ 
            BufferedWriter w = new BufferedWriter(new FileWriter(nomFitxer));
            for (int i = 0; i < this.getNumUsuaris(); i++){
                w.append(this.getUsuari(i).toCSV());
            }
            w.close();
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }
    
    /**
     * Obté una representació en text de la llista
     * @return Cadena amb tots els usuaris
     */
    @Override
    public String toString() {
        String info = "LLISTA USUARIS amb " + numUsuaris + " usuaris:\n";
        for (int i = 0; i < numUsuaris; i++) {
            info += llista[i].toString();
        }
        return info;
    }
}

