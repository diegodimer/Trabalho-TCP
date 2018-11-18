package Biblioteca;

import java.util.ArrayList;

public interface DatabaseInterface {

	Usuario findUser(String nome, String senha);

	boolean addUser(Usuario user);

	void tornaUserAdm(String nomeAdm);

	void listaAlgueisdoUsuario(Usuario user);

	ArrayList<ExemplarFisico> listaExemplaresDisponiveis(String nome);

	ArrayList<ExemplarFisico> listaExemplaresDisponiveisPorAutor(String nome);

	ArrayList<ExemplarFisico> listaExemplaresDisponiveisPorEditora(String nome);

	ArrayList<Titulo> listaTitulos(String nome);

	ArrayList<Editora> listaEditoras(String nome);

	ArrayList<Autor> listaAutores(String nome);

	ArrayList<Usuario> listaUsuarios(String nome);

	ArrayList<ExemplarFisico> listaExemplaresDisponiveisPorCategoria(String nome);

	ExemplarFisico findExemplarFisico(int idTitulo);

	boolean addAutor(Autor autor);

	Autor findAutor(String nome);

	boolean addCategoriaPorTitulo(int idtitulo, int idcat);

	boolean addEditora(Editora ed);

	Editora findEditora(String nome);

	boolean addCategoria(Categoria cat);

	Categoria findCategoria(String nome);

	boolean addTitulo(Titulo livro);

	Titulo findTitulo(String nome);

	Titulo findTitulo(Titulo titulo);

	Titulo findTitulo(int idTitulo);

	boolean removeTitulo(int idtitulo);

	boolean removeAutor(int idau);

	boolean removeEditora(int ided);

	boolean removeUsuario(int idu);

	boolean addExemplarFisico(int idTitulo, int numDisponiveis);

	boolean addExemplarOnline(int idTitulo, String link);

	ArrayList<ExemplarOnline> listaExemplarOnlinePorTitulo(String nome);

	boolean adicionaAluguelAtivo(int idUser, Titulo livro);

	void devolveLivroAlugado(int userId, int livroId);

	ArrayList<ExemplarAlugado> listaExemplaresDevolvidos(int userID);

	ArrayList<ExemplarOnline> listaExemplarOnlinePorAutor(String nome) throws DatabaseInoperanteException;

	ArrayList<ExemplarOnline> listaExemplarOnlinePorEditora(String nome) throws DatabaseInoperanteException;

	ArrayList<ExemplarOnline> listaExemplaresOnlineDisponiveisPorCategoria(String nome)
			throws DatabaseInoperanteException;

}