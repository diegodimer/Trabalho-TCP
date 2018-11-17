package Biblioteca;

public class UsuarioNaoEncontradoException extends DatabaseInoperanteException {
	/**
	 * Construtor para erro de database, usuario nao encontrado
	 * @param string mensagem de erro
	 */
	public UsuarioNaoEncontradoException(String string) {
		super(string);
	}

	private static final long serialVersionUID = -7308799838050601553L;

}
