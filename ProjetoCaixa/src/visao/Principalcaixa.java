package visao;

import java.awt.EventQueue;
import controle.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Principalcaixa {

	private JFrame frame;
	FluxoCaixa fc = new FluxoCaixa();
	private JTextField CampoConta;
	private JTextField CampoValor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principalcaixa window = new Principalcaixa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principalcaixa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("saque");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setOperacao("saque"); 
				fc.setCampoConta(CampoConta);
				fc.setCampoValor(CampoValor);
			
			}});
		btnNewButton.setBounds(230, 182, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("dep\u00F3sito");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setOperacao("deposito");
				fc.setCampoConta(CampoConta);
				fc.setCampoValor(CampoValor);
			}
		});
		btnNewButton_1.setBounds(331, 182, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("iniciar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setEncerrar(false);
				fc.start();
			}
		});
		btnNewButton_2.setBounds(23, 143, 97, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		CampoConta = new JTextField();
		CampoConta.setBounds(54, 94, 116, 22);
		frame.getContentPane().add(CampoConta);
		CampoConta.setColumns(10);
		
		JLabel lblConta = new JLabel("conta");
		lblConta.setBounds(79, 65, 56, 16);
		frame.getContentPane().add(lblConta);
		
		CampoValor = new JTextField();
		CampoValor.setBounds(198, 94, 116, 22);
		frame.getContentPane().add(CampoValor);
		CampoValor.setColumns(10);
		
		JLabel lblValor = new JLabel("valor");
		lblValor.setBounds(235, 65, 56, 16);
		frame.getContentPane().add(lblValor);
		
		JButton botEncerrar = new JButton("Encerrar");
		botEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setEncerrar(true);
			}
		});
		botEncerrar.setBounds(23, 228, 97, 25);
		frame.getContentPane().add(botEncerrar);
	}
}
