package Biblioteca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class Database implements DatabaseInterface {
	private String usuario;
	private String senha;
	private String url;
	private static Connection conec = null;

	//singleton
	 /**
	  * Conecta a classe usando o JDBC com a database do Heroku para executar as querys. � usada junto com o construtor 
	  * para formar o Singleton (s� uma conex�o poss�vel).
	  * @return            retorna um objeto Connection
	  * @throws DatabaseInoperanteException se n�o consegue a conex�o com a base de dados
	  */
	private Connection conectaDatabase(){
		Connection conec;
		url= "jdbc:postgresql://ec2-107-21-98-165.compute-1.amazonaws.com:5432/d3bt62m75hbk3k";
		usuario= "eiyrmkkfmwgvay";
		senha = "314fa4d9877c8f5f19446f16ec2bbde64767e9e348d2eaa9851e87006959b978";

		try {
			Class.forName("org.postgresql.Driver");
			conec = DriverManager.getConnection(url, usuario, senha);
			return conec;
		} catch (Exception e) {
			throw new DatabaseInoperanteException("Erro na abertura da database");
		}

	}
	
	Database() {
		if(conec == null) {
			Database.conec = conectaDatabase();
		}
	}

	 /**
	  * Encontra o usu�rio na tabela de usu�rios da base dados. Usada para o login do usu�rio no sistema.
	  * 
	  * @param nome   Nome do usu�rio a ser buscado
	  * @param senha    Senha do usu�rio
	  * @return         Objeto da classe usu�rio, contendo todos os seus dados.
	  * @throws UsuarioNaoEncontradoException se n�o encontra o usu�rio com o nome e senha informados na base de dados.
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#findUser(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario findUser(String nome, String senha) throws UsuarioNaoEncontradoException, DatabaseInoperanteException {

		PreparedStatement st;
		boolean UserFound = false;
		try {
			st = conec.prepareStatement("SELECT * FROM Usuario WHERE nome= ? and senha= ?");
			st.setString(1, nome);
			st.setString(2, senha);
			Usuario user= new Usuario();
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				user = new Usuario(rs.getString(2), rs.getString(3),rs.getString(4));
				user.setDebito(rs.getInt(5));
				user.setUserid(rs.getInt(1));
				user.setADM(rs.getBoolean(6));

				UserFound = true;
			}
			rs.close();
			st.close();

			if (UserFound)
			{
				return user;
			}
			else
				throw new UsuarioNaoEncontradoException("Usuario nao encontrado!"); // � insconsistencia na BD, mas n�.
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}


	}

	 /**
	  * Adiciona um usu�rio � tabela Usu�rio da base de dados. A consist�ncia dos dados � assegurada pela interface de Registro, 
	  * que controla o tamanho do nome, senha, etc. Pela database existe apenas a checagem de o nome de usu�rio e e-mail serem
	  * �nicos. � usada para o registro de novos usu�rios no sistema.
	  * @param user   Objeto da classe Usuario.
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados, provavelmente gerado por multiplos usu�rios com mesmo nome
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#addUser(Biblioteca.Usuario)
	 */
	@Override
	public boolean addUser(Usuario user) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO Usuario(nome,senha,email,debito,adm) VALUES (?, ?, ?, ?, ?)");
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			st.setInt(4, 0);
			st.setBoolean(5, false);
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Usu�rio j� existente");
		}
	}

	 /**
	  * Torna o usu�rio com o nome nomeAdm administrador. A fun��o atualiza o campo isAdm para true na tabela Usu�rios. Usada na
	  * interface do funcion�rio para tornar novos usu�rios administradores.
	  * 
	  * @param nomeAdm   nome do usu�rio a ser promovido administrador
	  * @throws UsuarioNaoEncontradoException se n�o encontra o usu�rio com o nome informado na base de dados.
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#tornaUserAdm(java.lang.String)
	 */
	@Override
	public void tornaUserAdm(String nomeAdm) throws DatabaseInoperanteException, UsuarioNaoEncontradoException {
		int i;

		try {
			PreparedStatement st = conec.prepareStatement("UPDATE Usuario set adm='true' where nome= ?");
			st.setString(1, nomeAdm);

			i = st.executeUpdate();
			i = st.getUpdateCount();
			st.close();
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
		if(i==0) {
			throw new UsuarioNaoEncontradoException("Usu�rio n�o encontrado!");
		}


	}
	
	/**
	  * Lista os alugueis ativos do usu�rio a partir da tabela AlugueisAtivos, onde tem o id do usu�rio e do livro alugado. � usada
	  * para listar os livros alugados pelo usu�rio na interface do usu�rio, com data de devolu��o e de aluguel.
	  * 
	  * @param user objeto da classe Usu�rio com os dados do usu�rio
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaAlgueisdoUsuario(Biblioteca.Usuario)
	 */
	@Override
	public void listaAlgueisdoUsuario(Usuario user) throws DatabaseInoperanteException {
		try {
			user.resetaAlugueis();
			PreparedStatement st = conec.prepareStatement("SELECT nomet, idtitulo, nomeau, idau, ided, nomeed, datadevolucao, dataemprestimo "
					+ " FROM AluguelAtivo JOIN Titulo ON(livro=idtitulo) JOIN usuario ON(usuario=idu) JOIN autor ON(autor=idau) JOIN editora ON (ided=editora) "
					+ " WHERE idu = ?");
			st.setInt(1, user.getUserid());
			ResultSet rs = null;
			rs = st.executeQuery();
			while (rs.next())
			{
				ExemplarAlugado novo = new ExemplarAlugado(rs.getString(1), rs.getInt(2));
				novo.setAutor(new Autor(rs.getString(3), rs.getInt(4)));
				novo.setEditora(new Editora(rs.getString(6), rs.getInt(5)));
				novo.setDataDevolucao(rs.getString(7));
				novo.setDataEmprestimo(rs.getString(8));
				user.adicionaNovoAluguel(novo); 
			}
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}

	
	/**
	  * Procura na tabela exemplarFisico t�tulos que contenham no nome a string informada como par�metro. Retorna uma lista de
	  * exemplares dispon�veis, objetos da classe ExemplarFisico. � usada para mostrar os livros que o usu�rio pode alugar.
	  * 
	  * @param nome t�tulo do livro a ser buscado (ou parte dele)
	  * @return lista de exemplares dispon�veis que contenham no nome o informado pelo parametro
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaExemplaresDisponiveis(java.lang.String)
	 */
	@Override
	public ArrayList<ExemplarFisico> listaExemplaresDisponiveis(String nome) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("SELECT nomet,nomeau,idau,nomeed,ided,idtitulo,num_disponiveis FROM Titulo JOIN autor ON(autor=idau) JOIN editora ON (ided=editora) JOIN exemplarfisico ON (livro = idtitulo) WHERE nomet LIKE ?");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<ExemplarFisico> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new ExemplarFisico(rs.getString(1), new Autor(rs.getString(2),rs.getInt(3)),new Editora(rs.getString(4),rs.getInt(5)),rs.getInt(6),rs.getInt(7)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}


	/**
	  * Procura na tabela exemplarFisico t�tulos que contenham no campo autor a string informada como par�metro. Retorna uma lista de
	  * exemplares dispon�veis, objetos da classe ExemplarFisico. Usada para mostrar livros que o usu�rio pode alugar.
	  * 
	  * @param nome nome do autor, ou parte dele
	  * @return lista com objetos da classe ExemplarFisico
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaExemplaresDisponiveisPorAutor(java.lang.String)
	 */
	@Override
	public ArrayList<ExemplarFisico> listaExemplaresDisponiveisPorAutor(String nome) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("SELECT nomet,nomeau,idau,nomeed,ided,idtitulo,num_disponiveis FROM Titulo JOIN autor ON(autor=idau) JOIN editora ON (ided=editora) JOIN exemplarfisico ON (livro = idtitulo) WHERE nomeau LIKE ?");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<ExemplarFisico> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new ExemplarFisico(rs.getString(1), new Autor(rs.getString(2),rs.getInt(3)),new Editora(rs.getString(4),rs.getInt(5)),rs.getInt(6),rs.getInt(7)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	
	/**
	  * Procura na tabela exemplarFisico t�tulos que contenham no campo editora a string informada como par�metro. Retorna uma lista de
	  * exemplares dispon�veis, objetos da classe ExemplarFisico. Usada para mostrar livros que o usu�rio pode alugar.
	  * 
	  * @param nome nome da editora, ou parte dele
	  * @return lista com objetos da classe ExemplarFisico
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaExemplaresDisponiveisPorEditora(java.lang.String)
	 */
	@Override
	public ArrayList<ExemplarFisico> listaExemplaresDisponiveisPorEditora(String nome) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("SELECT nomet,nomeau,idau,nomeed,ided,idtitulo,num_disponiveis FROM Titulo JOIN autor ON(autor=idau) JOIN editora ON (ided=editora) JOIN exemplarfisico ON (livro = idtitulo) WHERE nomeed LIKE ?");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<ExemplarFisico> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new ExemplarFisico(rs.getString(1), new Autor(rs.getString(2),rs.getInt(3)),new Editora(rs.getString(4),rs.getInt(5)),rs.getInt(6),rs.getInt(7)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	
	/**
	  * Procura na tabela Titulo t�tulos que contenham no campo nome a string informada como par�metro. Retorna uma lista de
	  * titulos, objetos da classe Titulo. Usada para listar os titulos da database, �til para o funcionario saber o id do livro
	  * e assim poder adicionar exemplares f�sicos ou online e remover t�tulos.
	  * 
	  * @param nome nome do livro, ou parte dele
	  * @return lista com objetos da classe Titulo
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaTitulos(java.lang.String)
	 */
	@Override
	public ArrayList<Titulo> listaTitulos(String nome) throws DatabaseInoperanteException{
		try {
			PreparedStatement st = conec.prepareStatement("SELECT nomet,nomeau,idau,nomeed,ided,idtitulo FROM Titulo JOIN autor ON(autor=idau) JOIN editora ON (ided=editora) WHERE nomet LIKE ?;");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<Titulo> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new Titulo(rs.getString(1), new Autor(rs.getString(2),rs.getInt(3)),new Editora(rs.getString(4),rs.getInt(5)),rs.getInt(6)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	
	/**
	  * Procura na tabela editora editoras que contenham no campo nome a string informada como par�metro. Retorna uma lista de
	  * editoras, objetos da classe Editora. Usada para listar as editoras da base de dados, �til na interface do usu�rio para
	  * poder deletar ou controlar os dados inseridos.
	  * 
	  * @param nome nome da editora, ou parte dele
	  * @return lista com objetos da classe Editora
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaEditoras(java.lang.String)
	 */
	@Override
	public ArrayList<Editora> listaEditoras(String nome) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("SELECT * FROM editora WHERE nomeed LIKE ?;");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<Editora> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new Editora(rs.getString(1),rs.getInt(2)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	
	/**
	  * Procura na tabela Autor autores que contenham no campo nome a string informada como par�metro. Retorna uma lista de
	  * autores, objetos da classe Autor. �til na interface do funcion�rio para controle de dados inseridos na base de dados.
	  * 
	  * @param nome nome do autor, ou parte dele
	  * @return lista com objetos da classe ExemplarFisico
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaAutores(java.lang.String)
	 */
	@Override
	public ArrayList<Autor> listaAutores(String nome) throws DatabaseInoperanteException{
		try {
			PreparedStatement st = conec.prepareStatement("SELECT * FROM autor WHERE nomeau LIKE ?;");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<Autor> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new Autor(rs.getString(1),rs.getInt(2)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	
	/**
	  * Procura na tabela Usuario usuarios que contenham no campo nome a string informada como par�metro. Retorna uma lista de
	  * usuarios, objetos da classe Usuario. Usada na interface do funcion�rio para excluir usu�rios.
	  * 
	  * @param nome nome do usuario, ou parte dele
	  * @return lista com objetos da classe Usuario
	  * @throws DatabaseInoperanteException se existe algum erro de conex�o com a base de dados
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaUsuarios(java.lang.String)
	 */
	@Override
	public ArrayList<Usuario> listaUsuarios(String nome) throws DatabaseInoperanteException{
		try {
			PreparedStatement st = conec.prepareStatement("SELECT nome,senha,email,idu FROM usuario WHERE nome LIKE ?;");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<Usuario> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaExemplaresDisponiveisPorCategoria(java.lang.String)
	 */
	@Override
	public ArrayList<ExemplarFisico> listaExemplaresDisponiveisPorCategoria(String nome) throws DatabaseInoperanteException{
		try {
			PreparedStatement st = conec.prepareStatement("SELECT nomet,nomeau,idau,nomeed,ided,idtitulo,num_disponiveis FROM Titulo JOIN autor ON(autor=idau) JOIN editora ON (ided=editora) JOIN exemplarfisico ON (livro = idtitulo) JOIN categoriaportitulo ON (idtitulo = categoriaportitulo.livro) JOIN categoria ON (categoriaportitulo.categoria =idca) WHERE nomeca LIKE ?;");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<ExemplarFisico> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new ExemplarFisico(rs.getString(1), new Autor(rs.getString(2),rs.getInt(3)),new Editora(rs.getString(4),rs.getInt(5)),rs.getInt(6),rs.getInt(7)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}

	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#findExemplarFisico(int)
	 */
	@Override
	public ExemplarFisico findExemplarFisico (int idTitulo) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("SELECT nomet,nomeau,idau,nomeed,ided,idtitulo,num_disponiveis FROM Titulo JOIN autor ON(autor=idau) JOIN editora ON (ided=editora) JOIN exemplarfisico ON (livro = idtitulo) where idtitulo = ? ");
			st.setInt(1, idTitulo);
			ResultSet rs = null;
			boolean livroAchado = false;
			rs = st.executeQuery();
			ExemplarFisico livro = null;
			while (rs.next())
			{
				livro = new ExemplarFisico(rs.getString(1), new Autor(rs.getString(2),rs.getInt(3)),new Editora(rs.getString(4),rs.getInt(5)),rs.getInt(6),rs.getInt(7));
				livroAchado = true;
			}
			if(livroAchado)
				return livro;
			else
				throw new DatabaseInoperanteException("Livro n�o encontrado!");
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}

	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#addAutor(Biblioteca.Autor)
	 */
	@Override
	public boolean addAutor(Autor autor) {
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO Autor(nomeau) VALUES (?)");
			st.setString(1, autor.getNome());
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}

	}




	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#findAutor(java.lang.String)
	 */
	@Override
	public Autor findAutor(String nome) throws AutorNaoEncontradoException, DatabaseInoperanteException{
		PreparedStatement st;
		boolean autorFound = false;
		try {
			st = conec.prepareStatement("SELECT * FROM Autor WHERE nomeau= ?");
			st.setString(1, nome);
			Autor autor= new Autor();
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				autor = new Autor(rs.getString(1), rs.getInt(2));
				autorFound = true;
			}
			rs.close();
			st.close();

			if (autorFound)
			{
				return autor;
			}
			else
				throw new AutorNaoEncontradoException("Autor n�o encontrado!"); //
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#addCategoriaPorTitulo(int, int)
	 */
	@Override
	public boolean addCategoriaPorTitulo(int idtitulo, int idcat) {
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO CategoriaPorTitulo(livro,categoria) VALUES (?,?)");
			st.setInt(1, idtitulo);
			st.setInt(2, idcat);
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
	}


	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#addEditora(Biblioteca.Editora)
	 */
	@Override
	public boolean addEditora(Editora ed) throws DatabaseInoperanteException{
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO Editora(nomeed) VALUES (?)");
			st.setString(1, ed.getNome());
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#findEditora(java.lang.String)
	 */
	@Override
	public Editora findEditora(String nome) throws EditoraNaoEncontradaException, DatabaseInoperanteException{
		PreparedStatement st;
		boolean UserFound = false;
		try {
			st = conec.prepareStatement("SELECT * FROM Editora WHERE nomeed= ?");
			st.setString(1, nome);
			Editora ed= new Editora();
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				ed = new Editora(rs.getString(1), rs.getInt(2));

				UserFound = true;
			}
			rs.close();
			st.close();

			if (UserFound)
			{
				return ed;
			}
			else
				throw new EditoraNaoEncontradaException("Editora nao encontrada!"); //
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}

	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#addCategoria(Biblioteca.Categoria)
	 */
	@Override
	public boolean addCategoria(Categoria cat) throws DatabaseInoperanteException{
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO categoria(nomeca) VALUES (?)");
			st.setString(1, cat.getNome());
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#findCategoria(java.lang.String)
	 */
	@Override
	public Categoria findCategoria(String nome) throws CategoriaNaoEncontradaException, DatabaseInoperanteException{
		PreparedStatement st;
		boolean UserFound = false;
		try {
			st = conec.prepareStatement("SELECT * FROM categoria WHERE nomeca= ?");
			st.setString(1, nome);
			Categoria cat= new Categoria();
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				cat = new Categoria(rs.getString(1), rs.getInt(2));

				UserFound = true;
			}
			rs.close();
			st.close();

			if (UserFound)
			{
				return cat;
			}
			else
				throw new CategoriaNaoEncontradaException("Categoria nao encontrada!"); //
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}




	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#addTitulo(Biblioteca.Titulo)
	 */
	@Override
	public boolean addTitulo(Titulo livro) {
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO titulo(nomet,autor,editora) VALUES (?, ?, ?)");
			st.setString(1, livro.getNome());

			Autor autor = livro.getAutor();
			st.setInt(2, autor.getId());

			Editora ed = livro.getEditora();	
			st.setInt(3, ed.getId());

			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database, id do autor ou id da editora possivelmente errados");
		}
	}

	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#findTitulo(java.lang.String)
	 */
	@Override
	public Titulo findTitulo(String nome) throws TituloNaoEncontradoException {
		PreparedStatement st;
		boolean UserFound = false;
		try {
			st = conec.prepareStatement("SELECT * FROM titulo JOIN autor ON (autor=idau) JOIN editora ON (editora = ided)  WHERE nomet= ?");
			st.setString(1, nome);
			Titulo titulo= new Titulo();
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				titulo = new Titulo(rs.getString(1), new Autor(rs.getString(5),rs.getInt(6)), new Editora(rs.getString(7),rs.getInt(8)),rs.getInt(4));

				UserFound = true;
			}
			rs.close();
			st.close();

			if (UserFound)
			{
				return titulo;
			}
			else
				throw new TituloNaoEncontradoException("titulo nao encontrado!");
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}

	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#findTitulo(Biblioteca.Titulo)
	 */
	@Override
	public Titulo findTitulo(Titulo titulo) throws TituloNaoEncontradoException {
		PreparedStatement st;
		boolean titleFound = false;
		try {
			st = conec.prepareStatement("SELECT * FROM titulo JOIN autor ON (autor=idau) JOIN editora ON (editora = ided)  WHERE nomet= ? and nomeau = ? and nomeed = ?");
			st.setString(1, titulo.getNome());
			st.setString(2, titulo.getAutor().getNome());
			st.setString(3, titulo.getEditora().getNome());
			Titulo tituloProcurado= new Titulo();
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				tituloProcurado = new Titulo(rs.getString(1), new Autor(rs.getString(5),rs.getInt(6)), new Editora(rs.getString(7),rs.getInt(8)),rs.getInt(4));

				titleFound = true;
			}
			rs.close();
			st.close();

			if (titleFound)
			{
				return tituloProcurado;
			}
			else
				throw new TituloNaoEncontradoException("titulo nao encontrado!");
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}


	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#findTitulo(int)
	 */
	@Override
	public Titulo findTitulo(int idTitulo) throws TituloNaoEncontradoException {
		PreparedStatement st;
		boolean titleFound = false;
		try {
			st = conec.prepareStatement("SELECT * FROM titulo JOIN autor ON (autor=idau) JOIN editora ON (editora = ided)  WHERE idtitulo= ?");
			st.setInt(1, idTitulo);
			Titulo tituloProcurado= new Titulo();
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				tituloProcurado = new Titulo(rs.getString(1), new Autor(rs.getString(5),rs.getInt(6)), new Editora(rs.getString(7),rs.getInt(8)),rs.getInt(4));

				titleFound = true;
			}
			rs.close();
			st.close();

			if (titleFound)
			{
				return tituloProcurado;
			}
			else
				throw new TituloNaoEncontradoException("titulo nao encontrado!");
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}



	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#removeTitulo(int)
	 */
	@Override
	public boolean removeTitulo(int idtitulo) {
		try {
			PreparedStatement st = conec.prepareStatement("DELETE FROM titulo WHERE idtitulo = ?");
			st.setInt(1, idtitulo);
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#removeAutor(int)
	 */
	@Override
	public boolean removeAutor(int idau) {
		try {
			PreparedStatement st = conec.prepareStatement("DELETE FROM autor WHERE idau = ?");
			st.setInt(1, idau);
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#removeEditora(int)
	 */
	@Override
	public boolean removeEditora(int ided) {
		try {
			PreparedStatement st = conec.prepareStatement("DELETE FROM editora WHERE ided = ?");
			st.setInt(1, ided);
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
	}
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#removeUsuario(int)
	 */
	@Override
	public boolean removeUsuario(int idu) {
		try {
			PreparedStatement st = conec.prepareStatement("DELETE FROM usuario WHERE idu = ?");
			st.setInt(1, idu);
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
	}

	void closeDatabase() throws SQLException {
		conec.close();
	}






	void criaTodasTabelas() {
		try {
			PreparedStatement st = conec.prepareStatement("create table Editora(\r\n" + 
					"	nomeed varchar(30) not null,\r\n" + 
					"	ided serial primary key,\r\n" + 
					"	unique (nomeed)\r\n" + 
					");\r\n" + 
					"");
			PreparedStatement st2 = conec.prepareStatement("create table Autor(\r\n" + 
					"	nomeau varchar(70) not null,\r\n" + 
					"	idau serial primary key,\r\n" + 
					"	unique (nomeau)\r\n" + 
					");");
			PreparedStatement st3 = conec.prepareStatement("create table Categoria(\r\n" + 
					"	nomeca varchar(30),\r\n" + 
					"	idca serial primary key,\r\n" + 
					"	unique (nomeca)\r\n" + 
					");");
			PreparedStatement st4 = conec.prepareStatement("create table Titulo(\r\n" + 
					"	nomet varchar(90),\r\n" + 
					"	autor integer references Autor\r\n" + 
					"	on update cascade\r\n" + 
					"	on delete cascade,\r\n" + 
					"	editora integer references Editora\r\n" + 
					"	on delete cascade\r\n" + 
					"	on update cascade,\r\n" + 
					"	idTitulo serial primary key,\r\n" + 
					"	unique (nomet, autor, editora)\r\n" + 
					");");
			PreparedStatement st5 = conec.prepareStatement("\r\n" + 
					"create table CategoriaPorTitulo(\r\n" + 
					"	livro integer references Titulo\r\n" + 
					"	on update cascade\r\n" + 
					"	on delete cascade,\r\n" + 
					"	categoria integer references Categoria\r\n" + 
					"	on update cascade\r\n" + 
					"	on delete cascade,\r\n" + 
					"	primary key(livro, categoria)\r\n" + 
					");\r\n" + 
					"");
			PreparedStatement st6 = conec.prepareStatement("create table ExemplarFisico(\r\n" + 
					"	livro integer references Titulo\r\n" + 
					"	on update cascade\r\n" + 
					"	on delete cascade,\r\n" + 
					"	num_disponiveis integer not null,\r\n" + 
					"	primary key (livro)\r\n" + 
					");\r\n" + 
					"");
			PreparedStatement st7 = conec.prepareStatement("create table ExemplarOnline(\r\n" + 
					"	livro integer references Titulo\r\n" + 
					"	on update cascade\r\n" + 
					"	on delete cascade,\r\n" + 
					"	link varchar(150),\r\n" + 
					"	primary key(livro)\r\n" + 
					");\r\n" + 
					"");
			PreparedStatement st8 = conec.prepareStatement("create table Usuario(\r\n" + 
					"	idu serial primary key,\r\n" + 
					"	nome varchar(30) not null unique,\r\n" + 
					"	senha varchar(30) not null,\r\n" + 
					"	email varchar(50) not null unique,\r\n" + 
					"	debito integer not null,\r\n" + 
					"	adm bool not null\r\n" + 
					");");
			PreparedStatement st9 = conec.prepareStatement("create table AluguelAtivo(\r\n" + 
					"	livro integer references ExemplarFisico\r\n" + 
					"	on update cascade \r\n" + 
					"	on delete cascade,\r\n" + 
					"	usuario integer references Usuario\r\n" + 
					"	on update cascade\r\n" + 
					"	on delete cascade,\r\n" + 
					"	dataEmprestimo date not null,\r\n" + 
					"	dataDevolucao date not null,\r\n" + 
					"	unique(livro, usuario)\r\n" + 
					");");
			PreparedStatement st10 = conec.prepareStatement("create table AluguelInativo(\r\n" + 
					"	livro integer references ExemplarFisico \r\n" + 
					"	on update cascade \r\n" + 
					"	on delete cascade,\r\n" + 
					"	usuario integer references Usuario\r\n" + 
					"	on update cascade\r\n" + 
					"	on delete cascade,\r\n" + 
					"	dataEmprestimo date not null,\r\n" + 
					"	dataDevolucao date not null\r\n" + 
					");");

			st.executeUpdate();
			st2.executeUpdate();
			st3.executeUpdate();
			st4.executeUpdate();
			st5.executeUpdate();
			st6.executeUpdate();
			st7.executeUpdate();
			st8.executeUpdate();
			st9.executeUpdate();
			st10.executeUpdate();

			st.close();
		}
		catch(Exception e) {
			throw new DatabaseInoperanteException("Erro na cria��o das tabelas");
		}
	}


	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#addExemplarFisico(int, int)
	 */
	@Override
	public boolean addExemplarFisico(int idTitulo, int numDisponiveis) {
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO ExemplarFisico(livro, num_disponiveis) VALUES (?, ?)");

			st.setInt(1, idTitulo);
			st.setInt(2, numDisponiveis);			
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Exemplar fisico n�o inserido! Erro!");
		}

	}
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#addExemplarOnline(int, java.lang.String)
	 */
	@Override
	public boolean addExemplarOnline(int idTitulo, String link) {
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO ExemplarOnline(livro, link) VALUES (?, ?)");

			st.setInt(1, idTitulo);
			st.setString(2, link);			
			st.executeUpdate();
			st.close();
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Exemplar online n�o inserido! Erro!");
		}

	}


	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaExemplarOnlinePorTitulo(java.lang.String)
	 */
	@Override
	public ArrayList<ExemplarOnline> listaExemplarOnlinePorTitulo(String nome) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("SELECT nomet,nomeau,idau,nomeed,ided,idtitulo,link FROM Titulo JOIN autor ON(autor=idau) JOIN editora ON (ided=editora) JOIN exemplaronline ON (livro = idtitulo) WHERE nomet LIKE ?");
			st.setString(1,"%"+ nome+"%");
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<ExemplarOnline> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new ExemplarOnline(rs.getString(1), new Autor(rs.getString(2),rs.getInt(3)),new Editora(rs.getString(4),rs.getInt(5)),rs.getInt(6),rs.getString(7)));

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
	}

	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#adicionaAluguelAtivo(int, Biblioteca.Titulo)
	 */
	@Override
	public boolean adicionaAluguelAtivo(int idUser, Titulo livro) {
		try {

			PreparedStatement st = conec.prepareStatement("INSERT INTO AluguelAtivo(livro, usuario, dataemprestimo, datadevolucao) VALUES (?, ?, ?, ?)");
			st.setInt(1, livro.getIdTitulo());
			st.setInt(2, idUser);
			LocalDate emprestimo = LocalDate.now();
			LocalDate devolucao = LocalDate.now().plusDays(5); 
			st.setObject(3, emprestimo);
			st.setObject(4, devolucao);
			st.executeUpdate();
			st.close();
			// tira um de num_disponiveis do exemplarFisico
			decrementaNumeroDisponiveis(livro.getIdTitulo());
			return true;
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Exemplar n�o pode ser alugado! Lembre-se: voc� s� pode ter uma c�pia de cada exemplar!");
		}		
	}

	private void incrementaNumeroDisponiveis(int livroID) throws SQLException {
		PreparedStatement st2 = conec.prepareStatement("UPDATE ExemplarFisico set num_Disponiveis = num_Disponiveis+1 where livro= ? ");
		st2.setInt(1, livroID);
		st2.executeUpdate();
		st2.close();
	}

	private void decrementaNumeroDisponiveis(int livroID) throws SQLException {
		PreparedStatement st2 = conec.prepareStatement("UPDATE ExemplarFisico set num_Disponiveis = num_Disponiveis-1 where livro= ? ");
		st2.setInt(1, livroID);
		st2.executeUpdate();
		st2.close();
	}
	
	
	private LocalDate consultaDataDevolucao(int userId, int livroId) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("Select dataemprestimo from AluguelAtivo where usuario = ? and livro = ?");
			st.setInt(1, userId);
			st.setInt(2, livroId);
			LocalDate dataEmprestimo = null;
			ResultSet rs = null;
			boolean aluguelFound = false;
			rs = st.executeQuery();
			while (rs.next())
			{
				aluguelFound = true;
				dataEmprestimo = rs.getObject(1, LocalDate.class);
			}
			st.close();
			if(aluguelFound)
				return dataEmprestimo;
			else 
				throw new DatabaseInoperanteException("Aluguel n�o encontrado!");
		}catch(Exception e) {
			throw new DatabaseInoperanteException("Erro na database!");
		}
	}

	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#devolveLivroAlugado(int, int)
	 */
	@Override
	public void devolveLivroAlugado(int userId, int livroId) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("INSERT INTO AluguelInativo(livro, usuario, dataemprestimo, datadevolucao) VALUES (?, ?, ?, ?)");
			st.setInt(1, livroId);
			st.setInt(2, userId);
			LocalDate emprestimo = consultaDataDevolucao(userId, livroId);
			st.setObject(3, emprestimo);
			st.setObject(4, LocalDate.now());
			st.executeUpdate();
			st.close();
			incrementaNumeroDisponiveis(livroId);
			
			
			removeAluguelAtivo(userId, livroId);
			
		}catch(SQLException e) {
			throw new DatabaseInoperanteException("Erro na database! Aluguel nao foi devolvido");
		}
	}

	private void removeAluguelAtivo(int userId, int livroId) throws SQLException {
		PreparedStatement st = conec.prepareStatement("delete FROM ALUGUELATIVO WHERE livro = ? and usuario = ?");
		st.setInt(1, livroId);
		st.setInt(2, userId);
		st.executeUpdate();
		st.close();
	}
	
	/* (non-Javadoc)
	 * @see Biblioteca.DatabaseInterface#listaExemplaresDevolvidos(int)
	 */
	@Override
	public ArrayList<ExemplarAlugado> listaExemplaresDevolvidos(int userID){
		try {
			PreparedStatement st = conec.prepareStatement("select nomet, nomeau, dataemprestimo, datadevolucao from AluguelInativo join titulo on (livro=idtitulo) join autor on (autor=idau) where usuario = ? ");
			st.setInt(1, userID);
			ResultSet rs = null;
			rs = st.executeQuery();
			ArrayList<ExemplarAlugado> lista = new ArrayList<>();
			while (rs.next())
			{
				lista.add(new ExemplarAlugado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)) );

			}
			return lista;
		} catch (SQLException e) {
			throw new DatabaseInoperanteException(e.getMessage());
		}
	}
}
