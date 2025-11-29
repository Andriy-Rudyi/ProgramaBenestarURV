package dades.inscripcions;

import dades.activitats.Activitat;
import dades.usuaris.Usuari;

public class LlistaInscripcions {
    private Inscripcions[] llista;
    private int numInscripcions;
    private int places;


    public LlistaInscripcions() {
        this.places = 100; //valor simbòlic en cas que no hagin especificat places
        this.llista = new Inscripcions[places];
        this.numInscripcions = 0;
    }

    
    public LlistaInscripcions(int places) {
        this.llista = new Inscripcions[places];
        this.numInscripcions = 0;
    }

   
    public void afegir(Inscripcions inscripcio) {
        if (numInscripcions < llista.length) {
            llista[numInscripcions] = inscripcio;
            numInscripcions++;
        } else {
            System.out.println("Error: Llista d'inscripcions plena."); //a futur: fer excepcio
        }
    }

    /**
     * Elimina un usuari d'una activitat i gestiona la llista d'espera.
     * Si hi ha algú esperant per aquesta activitat, entra automàticament.
     * @param usuari L'usuari que es vol donar de baixa.
     * @param activitat L'activitat que ees vol esborrar.
     */
    public void eliminarInscripcio(Usuari usuari, Activitat activitat) {
        int posicio = buscarPosicio(usuari, activitat);

        if (posicio != -1) {
            // buscarPosicio retorna -1 en cas de que no hagim trobat el que s'especificava, per tant desplacem nomes
            for (int i = posicio; i < numInscripcions - 1; i++) {
                llista[i] = llista[i + 1];
            }
            llista[numInscripcions - 1] = null; // Netegem l'últim duplicat
            numInscripcions--;
            
            System.out.println("L'usuari " + usuari.getAlies() + " s'ha eliminat de " + activitat.getNom());
            //Replenem la llista amb el que hi havia a la llista d'espera
            LlistaEspera espera = activitat.getLlistaEspera();
            Usuari nouUsuari = espera.treurePrimer(); 
            if (nouUsuari != null) {
                Inscripcions novaInscripcio = new Inscripcions(nouUsuari, activitat);
                this.afegir(novaInscripcio);
                System.out.println("AUTOMÀTIC: L'usuari " + nouUsuari.getAlies() + " entra des de la llista d'espera.");
            }

        } else {
            System.out.println("Error: No s'ha trobat la inscripció.");
        }
    }

    // Busquem un element
    public boolean hiEs(Usuari usuari, Activitat activitat) {
        return buscarPosicio(usuari, activitat) != -1;
    }

    /**
     * Mètode per trobar la posició a l'array.
     * @return index de la inscripció o -1
     */
    private int buscarPosicio(Usuari usuari, Activitat activitat) {
        for (int i = 0; i < numInscripcions; i++) {
            // Comparem l'alias de l'usuari i el nom de l'activitat per estar segurs
            if (llista[i].getUsuari().equals(usuari) && 
                llista[i].getActivitat().getNom().equals(activitat.getNom())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String resultat = "LLISTA D'INSCRIPCIONS (" + numInscripcions + "):\n";
        for (int i = 0; i < numInscripcions; i++) {
            resultat += (i + 1) + ". " + llista[i].toString() + "\n";
        }
        return resultat;
    }
}
