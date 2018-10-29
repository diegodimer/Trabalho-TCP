package Biblioteca;
import java.sql.*;


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
			e.printStackTrace();
		}
	}

	public Usuario findUser(String nome, String senha) throws SQLException {
		PreparedStatement st = conec.prepareStatement("SELECT * FROM Usuario WHERE nome= ? and senha= ?");
		st.setString(1, nome);
		st.setString(2, senha);
		boolean UserFound = false;
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
			throw new SQLException("Dois usuarios iguais!"); // é insconsistencia na BD, mas né.
	
	}

	public boolean addTitulo(Titulo livro) {
		return false;
	}

	public Titulo findTitulo(Titulo livro) {
		return null;
	}

	public boolean removeTitulo(Titulo livro) {
		return false;
	}

	public boolean addUser(Usuario user) {
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
			e.printStackTrace();
			return false;
		}
	}
	public void tornaUserAdm(String nomeAdm) {
		try {
			PreparedStatement st = conec.prepareStatement("UPDATE Usuario set adm='true' where nome= ?");
			st.setString(1, nomeAdm);
			st.executeUpdate();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	public boolean atualizaUsuario(Usuario user) {
		return false;
		
	}
	
	void closeDatabase() throws SQLException {
		conec.close();
	}
	

}
