package Biblioteca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
	private String usuario;
	private String senha;
	private String url;
	private Connection conec;
	
	// esse método construtor faz a conexão com o banco de dados. Até então eu fiz só printar se ta tudo ok
	Database() {
		url= "jdbc:postgresql://ec2-107-21-98-165.compute-1.amazonaws.com:5432/d3bt62m75hbk3k";
		usuario= "eiyrmkkfmwgvay";
		senha = "314fa4d9877c8f5f19446f16ec2bbde64767e9e348d2eaa9851e87006959b978";
		
		try {
			Class.forName("org.postgresql.Driver");
			conec = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexão feita!");
		} catch (Exception e) {
			throw new DatabaseInoperanteException("Erro na abertura da database");
		}
	}

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
				throw new UsuarioNaoEncontradoException("Usuario nao encontrado!"); // é insconsistencia na BD, mas né.
		} catch (SQLException e) {
			throw new DatabaseInoperanteException("Erro na database");
		}
		
	
	}

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
			throw new DatabaseInoperanteException("Usuário já existente");
		}
	}
	
	public void tornaUserAdm(String nomeAdm) throws DatabaseInoperanteException {
		try {
			PreparedStatement st = conec.prepareStatement("UPDATE Usuario set adm='true' where nome= ?");
			st.setString(1, nomeAdm);
			st.executeUpdate();
			st.close();
		}
		catch(Exception e)
		{
			throw new DatabaseInoperanteException("Erro na database");
		}
		
		
	}
	public void listaAlgueisdoUsuario(Usuario user) throws DatabaseInoperanteException, SQLException {
		try {
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
	
	public ArrayList<ExemplarFisico> listaExemplaresDisponiveis(String nome) throws DatabaseInoperanteException, SQLException {
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
	
	
	public boolean atualizaUsuario(Usuario user) {
		return false;
		
	}

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

	public boolean removeTitulo(Titulo livro) {
		return false;
	}

	
	void closeDatabase() throws SQLException {
		conec.close();
	}


	
	
	
	
	void criaTodasTabelas() {
		try {
		PreparedStatement st = conec.prepareStatement("create table Editora(\r\n" + 
				"	nomeed varchar(30) not null,\r\n" + 
				"	ided serial primary key,\r\n" + 
				"	unique (nomeed, ided)\r\n" + 
				");\r\n" + 
				"");
		PreparedStatement st2 = conec.prepareStatement("\r\n" + 
				"create table Autor(\r\n" + 
				"	nomeau varchar(70) not null,\r\n" + 
				"	idau serial primary key,\r\n" + 
				"	unique (nomeau, idau)\r\n" + 
				");");
		PreparedStatement st3 = conec.prepareStatement("\r\n" + 
				"create table Categoria(\r\n" + 
				"	nomeca varchar(30),\r\n" + 
				"	idca serial primary key,\r\n" + 
				"	unique (nomeca, idca)\r\n" + 
				");\r\n" + 
				"");
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
		PreparedStatement st5 = conec.prepareStatement("create table CategoriaPorTitulo(\r\n" + 
				"	livro integer references Titulo\r\n" + 
				"	on update cascade\r\n" + 
				"	on delete cascade,\r\n" + 
				"	categoria integer references Categoria\r\n" + 
				"	on update cascade\r\n" + 
				"	on delete cascade,\r\n" + 
				"	primary key(livro, categoria)\r\n" + 
				");");
		PreparedStatement st6 = conec.prepareStatement("\r\n" + 
				"create table ExemplarFisico(\r\n" + 
				"	livro integer references Titulo\r\n" + 
				"	on update cascade\r\n" + 
				"	on delete cascade,\r\n" + 
				"	num_disponiveis integer not null,\r\n" + 
				"	primary key (livro)\r\n" + 
				");");
		PreparedStatement st7 = conec.prepareStatement("\r\n" + 
				"create table ExemplarOnline(\r\n" + 
				"	livro integer references Titulo\r\n" + 
				"	on update cascade\r\n" + 
				"	on delete cascade,\r\n" + 
				"	link varchar(30)\r\n" + 
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
				"	dataDevolucao date not null\r\n" + 
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
			throw new DatabaseInoperanteException("Erro na criação das tabelas");
		}
	}
}
