package interficie;
import java.awt.event.*;
import javax.swing.*;

import aplicacio.AppInterficie;
import dades.activitats.Activitat;
import dades.excepcions.DataIncorrectaExcepction;

public class AccioColectiuCanviat implements ActionListener {
	
	private AppInterficie finestra;
	
	public AccioColectiuCanviat(AppInterficie finestra) {
		this.finestra = finestra;
	}
	
	// Què passa quan es fa "click"?
	public void actionPerformed(ActionEvent evt) {
		// Obtenim la referència del control que ha provocat l'esdeveniment.
		JComboBox<?> b = (JComboBox<?>) evt.getSource();
		// Obtenim el text del botó.
		String s = (String) b.getSelectedItem();
        if (s.equals("Diàries")) s = Activitat.TIPUS_UNDIA;
		else if (s.equals("Periòdiques")) s = Activitat.TIPUS_PERIODICA;
        else if (s.equals("Online")) s = Activitat.TIPUS_ONLINE;
        // si no, s = "Totes"
        finestra.setColectiu(s);
        try {
            finestra.dibuixarCalendari(finestra.getMes(), finestra.getAny());
        } catch (DataIncorrectaExcepction e) {
            throw new IllegalAccessError("Error intern redibuixant el calendari. " + e);
        }
	}
	
}