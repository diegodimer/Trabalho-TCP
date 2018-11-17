package Biblioteca;

public class TituloNaoEncontradoException extends DatabaseInoperanteException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2890878925899289654L;
	/**
	 * Construtor para erro de database, titulo nao encontrado
	 * @param string mensagem de erro
	 */
	public TituloNaoEncontradoException(String string) {
		super(string);
	}
}
