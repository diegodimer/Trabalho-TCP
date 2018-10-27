package Biblioteca;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	@Test
	void testAddUser() {

		//Usuario novo = new Usuario("Diego", "123", "diego.dimer@ufrgs.br");
		//assertTrue(testeDB.addUser(novo));
	}
	
	@Test
	void testFindUser() throws SQLException {

		Usuario testuser;
		testuser = testeDB.findUser("Diego", "123");
		assertEquals("Diego", testuser.getUsername());
		assertTrue(testuser.isADM());
	}

}
