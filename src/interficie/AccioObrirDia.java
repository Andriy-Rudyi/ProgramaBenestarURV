package interficie;
import java.awt.event.*;
import javax.swing.*;

import aplicacio.AppInterficie;

public class AccioObrirDia implements ActionListener {
	
	private AppInterficie finestra;
	
	public AccioObrirDia(AppInterficie finestra) {
		this.finestra = finestra;
	}
	
	// Què passa quan es fa "click"?
	public void actionPerformed(ActionEvent evt) {
		// Obtenim la referència del control que ha provocat l'esdeveniment.
		JButton b = (JButton) evt.getSource();
		// Obtenim el text del botó.
		String s = b.getText();
		
        finestra.obrirDialegDia(Integer.parseInt(s));
	}
	
}