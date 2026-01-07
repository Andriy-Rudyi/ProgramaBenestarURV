package interficie;
import java.awt.event.*;
import javax.swing.*;

import aplicacio.AppInterficie;
import dades.excepcions.DataIncorrectaExcepction;

public class AccioCanviarMes implements ActionListener {
	
	private AppInterficie finestra;
	
	public AccioCanviarMes(AppInterficie finestra) {
		this.finestra = finestra;
	}
	
	// Què passa quan es fa "click"?
	public void actionPerformed(ActionEvent evt) {
		// Obtenim la referència del control que ha provocat l'esdeveniment.
		JButton b = (JButton) evt.getSource();
		// Obtenim el text del botó.
		String s = b.getText();
		if (s.equals("<")) finestra.disminuirMes();
        else if (s.equals(">")) finestra.augmentarMes();
        try {
            finestra.dibuixarCalendari(finestra.getMes(), finestra.getAny());
        } catch (DataIncorrectaExcepction e) {
            throw new IllegalStateException("Error intern. " + e);
        }
	}
	
}
