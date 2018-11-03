package Biblioteca;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.RootPaneContainer;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class UsuarioUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Database dataBase;
	private Usuario user;
	
	// pra ser editado em outra função nao pode ser de uma função
	private JTextField conteudoBuscado;
	
	// BOTÕES
	private JButton btnAlugarNovoLivro;
	private JButton btnDevolverLivro;
	private JButton btnHistoricoDeEmprestimos;
	private JButton btnBuscar;
	private JTextArea resultadoBusca;
	
	// RADIO BUTTONS
	private final ButtonGroup opcaoBusca = new ButtonGroup(); // grupo de botoes
	private JRadioButton rdbtnEditora;
	private JRadioButton rdbtnCategoria;
	private JRadioButton rdbtnNome;
	private JRadioButton rdbtnAutor;
	
	public UsuarioUI(Usuario user, Database dataBase) {
		setResizable(false);
		setTitle("Interface do Usuario");
		this.dataBase = dataBase;
		this.user = user;
		
		try {
			dataBase.listaAlgueisdoUsuario(user);
		} catch (DatabaseInoperanteException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 241, 425, 76);
		contentPane.add(scrollPane);
		
		JPanel panelComAlugueis = new JPanel();
		scrollPane.setViewportView(panelComAlugueis);
		
		JTextArea listadeAlugueis = new JTextArea();
		listadeAlugueis.setEditable(false);
		panelComAlugueis.add(listadeAlugueis);
		
		
		listadeAlugueis.setText(user.listaAlugueisAtivos());
		
		JLabel lblAlugueisAtivos = new JLabel("Alugueis Ativos");
		lblAlugueisAtivos.setFont(new Font("Monospaced", Font.PLAIN, 15));
		lblAlugueisAtivos.setBounds(10, 220, 184, 19);
		contentPane.add(lblAlugueisAtivos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(27, 210, 376, 29);
		contentPane.add(separator);
		
		btnAlugarNovoLivro = new JButton("Alugar novo livro");
		btnAlugarNovoLivro.addActionListener(this);
		btnAlugarNovoLivro.setBounds(27, 156, 151, 29);
		contentPane.add(btnAlugarNovoLivro);
		
		btnDevolverLivro = new JButton("Devolver livro");
		btnDevolverLivro.addActionListener(this);
		btnDevolverLivro.setBounds(27, 116, 151, 29);
		contentPane.add(btnDevolverLivro);
		
		btnHistoricoDeEmprestimos = new JButton("Hist\u00F3rico de empr\u00E9stimos");
		btnHistoricoDeEmprestimos.addActionListener(this);
		btnHistoricoDeEmprestimos.setBounds(27, 76, 151, 29);
		contentPane.add(btnHistoricoDeEmprestimos);
		
		JScrollPane scrollBusca = new JScrollPane();
		scrollBusca.setBounds(199, 65, 225, 120);
		contentPane.add(scrollBusca);
		
		JPanel paneldaBusca = new JPanel();
		scrollBusca.setViewportView(paneldaBusca);
		
		resultadoBusca = new JTextArea();
		resultadoBusca.setEditable(false);
		paneldaBusca.add(resultadoBusca);
		
		JLabel lblBuscarLivro = new JLabel("Buscar livro");
		lblBuscarLivro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBuscarLivro.setBounds(10, 9, 90, 19);
		contentPane.add(lblBuscarLivro);
		
		conteudoBuscado = new JTextField();
		conteudoBuscado.setBounds(110, 10, 212, 19);
		contentPane.add(conteudoBuscado);
		conteudoBuscado.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(332, 31, 89, 23);
		contentPane.add(btnBuscar);
		
		JPanel panelRadioButtons = new JPanel();
		panelRadioButtons.setBounds(10, 31, 355, 23);
		contentPane.add(panelRadioButtons);
		
		rdbtnNome = new JRadioButton("Nome");
		opcaoBusca.add(rdbtnNome);
		rdbtnNome.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadioButtons.add(rdbtnNome);
		
		rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setMnemonic('0');
		opcaoBusca.add(rdbtnAutor);
		rdbtnAutor.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadioButtons.add(rdbtnAutor);
		
		rdbtnEditora = new JRadioButton("Editora");
		rdbtnEditora.setMnemonic('1');
		opcaoBusca.add(rdbtnEditora);
		rdbtnEditora.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadioButtons.add(rdbtnEditora);
		
		rdbtnCategoria = new JRadioButton("Categoria");
		rdbtnCategoria.setMnemonic('2');
		opcaoBusca.add(rdbtnCategoria);
		rdbtnCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadioButtons.add(rdbtnCategoria);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBuscar) {
			if(rdbtnNome.isSelected()) {
				StringBuilder str = new StringBuilder();
				
				try {
				for (Iterator<ExemplarFisico> iterator = dataBase.listaExemplaresDisponiveis(conteudoBuscado.getText()).iterator(); iterator.hasNext();) {
					ExemplarFisico exemplarFisico = iterator.next();
					str.append("Nome: " + exemplarFisico.getNome() + "\n\tAutor: " + exemplarFisico.getAutor().getNome() + "\n\tEditora: " + exemplarFisico.getEditora().getNome() + "\n\tNº Disponiveis: " + exemplarFisico.getNumDisponiveis() +"\n\n");
				}
				resultadoBusca.setText(str.toString());
				}
				catch(SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
			}
			else if(rdbtnEditora.isSelected()) {
				
			}
			else if(rdbtnCategoria.isSelected()) {
				
			}
			else if(rdbtnAutor.isSelected()){
			}
			else {
				JOptionPane.showMessageDialog(null, "Escolha um parametro para buscar!");
			}
		}
	}
}
