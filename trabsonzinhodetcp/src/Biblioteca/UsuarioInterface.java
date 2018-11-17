package Biblioteca;

public interface UsuarioInterface {

	void listaHistoricoDeAlugueis();

	void devolveLivros(int livroId);

	void listaAlugueisAtivos();

	void alugarLivroFisico(int idLivro);

	void alugarLivroOnline(int idLivro);

}