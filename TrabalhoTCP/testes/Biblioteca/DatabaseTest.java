package Biblioteca;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseTest {
	Database testeDB;
	
	@BeforeEach
	void setUpClass() throws Exception {
		testeDB = new Database();
	}

	@AfterEach
	void tearDownClass() throws Exception {
		testeDB.closeDatabase();
	}

	// comentem esse depois de fazer! a db só suporta um usuario de cada tipo.
	@Test
	void testAddUser() {
		testeDB.criaTodasTabelas();
		Usuario novo = new Usuario("Diego", "123", "diego.dimer@ufrgs.br");
		assertTrue(testeDB.addUser(novo));
		
	}
	
	
	
	@Test
	void testtornaUserAdm(){
		testeDB.tornaUserAdm("Diego");
	}

	@Test
		void testFindUserExistente(){
			Usuario testuser = null;
			try {
				testuser = testeDB.findUser("Diego", "123");
			} catch (DatabaseInoperanteException | UsuarioNaoEncontradoException e) {
				fail("erro");
			}
			assertEquals("Diego", testuser.getUsername());
			assertTrue(testuser.isADM());
			
		
		}
	
	@Test
		void testFindUserInexistente() {
		Usuario usuarioTeste = null;
	}
}
