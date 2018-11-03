package Biblioteca;

@SuppressWarnings("serial")
public class AutorNaoEncontradoException extends Exception {

	public AutorNaoEncontradoException() {		
	}

	public AutorNaoEncontradoException(String arg0) {
		super(arg0);
	}

	public AutorNaoEncontradoException(Throwable arg0) {
		super(arg0);	
	}

	public AutorNaoEncontradoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AutorNaoEncontradoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
