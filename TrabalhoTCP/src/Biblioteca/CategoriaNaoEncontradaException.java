package Biblioteca;

public class CategoriaNaoEncontradaException extends DatabaseInoperanteException{

	private static final long serialVersionUID = -8359478798305477363L;
	/**
	 * Construtor para erro de database, categoria nao encontrada
	 * @param string mensagem de erro
	 */
	public CategoriaNaoEncontradaException(String string) {
		super(string);
	}
	
}
