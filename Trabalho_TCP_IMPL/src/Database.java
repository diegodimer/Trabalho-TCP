import java.sql.*;

public class Database {
	private String usuario;
	private String senha;
	private String url;
	private Connection conec;
	
	// esse m�todo construtor faz a conex�o com o banco de dados. At� ent�o eu fiz s� printar se ta tudo ok
	Database() {
		url= "jdbc:postgresql://localhost:5432/biblioteca_TCP";
		usuario= "postgres";
		senha = "prosgres";
		
		try {
			Class.forName("org.postgresql.Driver");
			conec = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conex�o feita!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usu�rio findUser(String data1, String data2) {
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

	public boolean addUser(Usu�rio user) {
		return false;
	}

	public boolean atualizaUsuario(Usu�rio user) {
		return false;
	}

}
