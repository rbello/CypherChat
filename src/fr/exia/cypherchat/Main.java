package fr.exia.cypherchat;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.exia.cypherchat.client.ClientWindow;
import fr.exia.cypherchat.client.Controller;
import fr.exia.cypherchat.client.Model;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				// Avoir le look windows normal
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) { }
				
				// Créer la fenêtre
				ClientWindow view = new ClientWindow();
				
				// Créer le modèle
				Model model = new Model();
				
				// Créer le controller
				Controller ctrl = new Controller(model, view);
				
				// Afficher la fenêtre
				view.setVisible(true);
			}
		});
		
	}

}
