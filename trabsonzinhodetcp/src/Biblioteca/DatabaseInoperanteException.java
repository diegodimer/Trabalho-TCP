package Biblioteca;

public class DatabaseInoperanteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8951820933920133564L;
	/**
	 * Construtor para erro de database
	 * @param string mensagem de erro
	 */
	public DatabaseInoperanteException(String string) {
		super(string);
	}

}
