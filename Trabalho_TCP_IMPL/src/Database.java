import java.sql.*;

public class Database {
	private String usuario;
	private String senha;
	private String url;
	private Connection conec;
	
	// esse método construtor faz a conexão com o banco de dados. Até então eu fiz só printar se ta tudo ok
	Database() {
		url= "jdbc:postgresql://localhost:5432/biblioteca_TCP";
		usuario= "postgres";
		senha = "prosgres";
		
		try {
			Class.forName("org.postgresql.Driver");
			conec = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conexão feita!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuário findUser(String data1, String data2) {
		return null;
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

	public boolean addUser(Usuário user) {
		return false;
	}

	public boolean atualizaUsuario(Usuário user) {
		return false;
	}

}
