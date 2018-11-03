package Biblioteca;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.UIManager;

public class Main {

	public Main() {
		
	}

	public static void main(String[] args) throws DatabaseInoperanteException, UsuarioNaoEncontradoException, SQLException {
			
		String tema_padrao = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {  
              UIManager.setLookAndFeel(tema_padrao);  
            } catch (Exception e) {  
        }
		LoginUI frame = new LoginUI();		EventQueue.invokeLater(new Runnable() {			public void run() {				try {					frame.setVisible(true);				} catch (Exception e) {					e.printStackTrace();				}			}		});
		
	}
}
