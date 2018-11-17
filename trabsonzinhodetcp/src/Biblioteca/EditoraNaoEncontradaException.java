package Biblioteca;

public class EditoraNaoEncontradaException extends DatabaseInoperanteException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1891537466927596130L;
	/**
	 * Construtor para erro de database, editora nao encontrada
	 * @param string mensagem de erro
	 */
	public EditoraNaoEncontradaException(String string) {
		super(string);
	}
	
	
}
