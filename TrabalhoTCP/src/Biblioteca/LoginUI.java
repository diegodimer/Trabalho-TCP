package Biblioteca;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoginUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private Database database;

	/**
	 * Create the frame.
	 */
	public LoginUI() {
		database = new Database(); // abre a database (por isso demora um pouco pra aparecer a tela)
		
		setTitle("Tela de login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTrabalhoDeTcp = new JLabel("Sistema de Biblioteca");
		lblTrabalhoDeTcp.setBounds(10, 28, 424, 27);
		lblTrabalhoDeTcp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrabalhoDeTcp.setFont(new Font("Tahoma", Font.PLAIN, 27));
		contentPane.add(lblTrabalhoDeTcp);
		
		usernameField = new JTextField();
		usernameField.setBounds(55, 103, 150, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(55, 134, 150, 20);
		contentPane.add(passwordField);
		
		JLabel lblUser = new JLabel("Usu\u00E1rio");
		lblUser.setBounds(10, 106, 46, 14);
		contentPane.add(lblUser);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 137, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblCriadoPor = new JLabel("Criado por:");
		lblCriadoPor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCriadoPor.setBounds(10, 199, 80, 14);
		contentPane.add(lblCriadoPor);
		
		JLabel lblAugustoPeiter = new JLabel("Augusto Peiter");
		lblAugustoPeiter.setBounds(10, 211, 90, 14);
		contentPane.add(lblAugustoPeiter);
		
		JLabel lblDiegoDimer = new JLabel("Diego Dimer");
		lblDiegoDimer.setBounds(10, 224, 90, 14);
		contentPane.add(lblDiegoDimer);
		
		JLabel lblEduardoPaim = new JLabel("Eduardo Paim");
		lblEduardoPaim.setBounds(10, 236, 90, 14);
		contentPane.add(lblEduardoPaim);
		
		btnLogin = new JButton("Log-in");
		btnLogin.setBounds(87, 165, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel memeLogin = new JLabel(new ImageIcon(LoginUI.class.getResource("/Biblioteca/memelogin.png")));
		memeLogin.setLocation(215, 66);
		memeLogin.setSize(219, 195);
		contentPane.add(memeLogin);
		
		btnLogin.addActionListener(this);
	}
	// esse m�todo � as a��es dos bot�es
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			Usuario user = new Usuario();
			
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(passwordField.getPassword());
			
			try {
				user = database.findUser(usernameField.getText(), stringBuilder.toString());
				if(user.isADM())
				{
					JOptionPane.showMessageDialog(null, "Login administrador feito!");
				}
				else
					JOptionPane.showMessageDialog(null, "Login usuario feito!");
			} catch (DatabaseInoperanteException | UsuarioNaoEncontradoException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage()); // erro mostra a mensagem daquela exce��o
			}
			
			
		}
	}
}