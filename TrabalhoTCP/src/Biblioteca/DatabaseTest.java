package Biblioteca;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseTest {
	Database testeDB;
	@BeforeEach
	void setUp() throws Exception {
		testeDB = new Database();
	}

	@AfterEach
	void tearDown() throws Exception {
		testeDB.closeDatabase();
	}

	// comentem esse depois de fazer! a db só suporta um usuario de cada tipo.
	//@Test
	void testAddUser() {

		//Usuario novo = new Usuario("Joao", "123", "blabla@ufrgs.br");
	//	assertTrue(testeDB.addUser(novo));
	}
	
	
	
	@Test
	void testtornaUserAdm(){
		testeDB.tornaUserAdm("Joao");
		
	}

	@Test
		void testFindUser() throws SQLException {
			Usuario testuser;
			testuser = testeDB.findUser("Joao", "123");
			assertEquals("Joao", testuser.getUsername());
			assertTrue(testuser.isADM());
			
		
		}
}
