package Biblioteca;
import java.util.ArrayList;

public class UsuarioUI {

	public void consultaremprestimos() {

	}

	public void pagarmulta() {

	}

	public void renovar() {

	}

	public void verificardisponibilidade() {

	}

	public abstract static class bibliotecaUI {

		private String usuario;

		private ArrayList<Titulo> titulos;

		private String userID;

		private Database database;

		private UsuarioUI[] usuarioUI;

		private FuncionarioUI[] funcionarioUI;

		public static void mostrarmenu() {

		}

		public static boolean login(String usuario, String senha) {
			return false;
		}

		public static boolean logout() {
			return false;
		}

		public static boolean cria_usuario() {
			return false;
		}

	}

}
