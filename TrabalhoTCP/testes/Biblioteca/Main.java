package Biblioteca;

import java.awt.EventQueue;
import java.sql.SQLException;

public class Main {

	public Main() {
		
	}

	public static void main(String[] args) throws DatabaseInoperanteException, UsuarioNaoEncontradoException, SQLException {
			
		LoginUI frame = new LoginUI();		EventQueue.invokeLater(new Runnable() {			public void run() {				try {					frame.setVisible(true);				} catch (Exception e) {					e.printStackTrace();				}			}		});
		
	}
}
