package Biblioteca;

@SuppressWarnings("serial")
public class AutorNaoEncontradoException extends DatabaseInoperanteException {

	/**
	 * Construtor para erro de database, autor nao encontrado
	 * @param arg0 mensagem de erro
	 */
	public AutorNaoEncontradoException(String arg0) {
		super(arg0);
	}

	

}
