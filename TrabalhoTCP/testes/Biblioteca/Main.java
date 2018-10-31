package Biblioteca;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Database DB = new Database();
		Usuario Eu = new Usuario();
		try {
			Eu = DB.findUser("Diego", "123");
		} catch (DatabaseInoperanteException | UsuarioNaoEncontradoException e) {
			System.out.println("a");
		}
		try {
		DB.listaAlgueisdoUsuario(Eu);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println((Eu.getAlugueis().get(0)).getNome());
		System.out.println((Eu.getAlugueis().get(1)).getNome());
		

	}

}
