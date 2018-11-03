package Biblioteca;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {
	static Database testeDB;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		testeDB = new Database();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		testeDB.closeDatabase();
	}

	// comentem esse depois de fazer! a db só suporta um usuario de cada tipo.
	//@Test
	public void testAddUser() {
		testeDB.criaTodasTabelas();
		Usuario novo = new Usuario("Diego", "123", "diego.dimer@ufrgs.br");
		assertTrue(testeDB.addUser(novo));
		
	}
	
	
	
	@Test
	public void testtornaUserAdm() throws UsuarioNaoEncontradoException{
		testeDB.tornaUserAdm("Diego");
	}

	@Test
	public void testFindUserExistente(){
			Usuario testuser = null;
			try {
				testuser = testeDB.findUser("Diego", "123");
			} catch (DatabaseInoperanteException | UsuarioNaoEncontradoException e) {
				fail("erro");
			}
			assertEquals("Diego", testuser.getUsername());
			assertTrue(testuser.isADM());
			
		
		}
	
	@Test(expected  = UsuarioNaoEncontradoException.class)
	public void testFindUserInexistente() throws UsuarioNaoEncontradoException {
		testeDB.findUser("Joao", "1234");
	}
	
	
	//@Test
	//comentei isso aqui para nao criar varias editoras iguais
	public void testAddEditora() {
		Editora novoEd = new Editora("Rocco");
		assertTrue(testeDB.addEditora(novoEd));
	}
	
	@Test(expected = EditoraNaoEncontradaException.class)
	public void testFindEditoraInexistente() throws EditoraNaoEncontradaException{
		testeDB.findEditora("editoraimaginaria");
	}
	
	@Test
	public void testFindEditoraExistente(){
			Editora ed = null;
			try {
				ed = testeDB.findEditora("Rocco");
			} catch (DatabaseInoperanteException | EditoraNaoEncontradaException e) {
				fail("erro");
			}
			assertEquals(1, ed.getId());
		}
	
	//@Test
	public void testAddCategoria() {
		Categoria cat = new Categoria("terror");
		assertTrue(testeDB.addCategoria(cat));
	}
	@Test(expected = CategoriaNaoEncontradaException.class)
	public void testFindCategoriaInexistente() throws CategoriaNaoEncontradaException{
		testeDB.findCategoria("categoriaimaginaria");
	}
	
	@Test
	public void testFindCategoriaExistente(){
			Categoria cat = null;
			try {
				cat = testeDB.findCategoria("terror");
			} catch (DatabaseInoperanteException | CategoriaNaoEncontradaException e) {
				fail("erro");
			}
			assertEquals(1, cat.getId());
		}
	@Test 
	public void testCriartitulo(){
		Titulo titulo = new Titulo("harry potter and the economic fall of the soviet union",new Autor("J. K. Stalin",3),new Editora("Positivo",2));
		Autor autor = titulo.getAutor();
		Editora ed =titulo.getEditora();
		System.out.println(titulo.getNome());
		System.out.println(Integer.toString(autor.getId()));
		System.out.println(Integer.toString(ed.getId()));
	}
	//@Test 
	public void testAddAutor() {
		Autor autor = new Autor("J. K. Stalin");
		assertTrue(testeDB.addAutor(autor));
	}
	
	//@Test
	public void testAddTitulo() {
		Titulo titulo = new Titulo("harry potter and the economic fall of the soviet union",new Autor("J. K. Stalin",1),new Editora("Rocco",1));
		assertTrue(testeDB.addTitulo(titulo));
	}
	@Test
	public void testTituloExistente(){
			Titulo titulo = null;
			try {
				titulo = testeDB.findTitulo("harry potter and the economic fall of the soviet union");
			} catch (DatabaseInoperanteException | TituloNaoEncontradoException e) {
				fail("erro");
			}
			assertEquals(3, titulo.getIdTitulo());
		}
	

	//@Test
	public void testListarAlugueis() throws DatabaseInoperanteException, UsuarioNaoEncontradoException {
		Usuario Eu = testeDB.findUser("Diego", "123");
		try {
			testeDB.listaAlgueisdoUsuario(Eu);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals((Eu.getAlugueis().get(0)).getNome(), "O Labirinto de Fogo");
	}
	
	@Test
	public void testListaExemplaresDisponiveis() throws DatabaseInoperanteException, UsuarioNaoEncontradoException {
		try {
			ArrayList<ExemplarFisico> lista = testeDB.listaExemplaresDisponiveis("fall");
			assertEquals((lista.get(0)).getNome(), "harry potter and the economic fall of the soviet union");
//			System.out.println((lista.get(0)).getNome()+"  "+ (lista.get(0)).getNumDisponiveis());
//			System.out.println((lista.get(1)).getNome()+"  "+ (lista.get(1)).getNumDisponiveis());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testAddExemplarFisico() {
		assertTrue(testeDB.addExemplarFisico(3, 5));
	}
}
