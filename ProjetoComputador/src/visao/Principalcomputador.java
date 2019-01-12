package visao;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import controle.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principalcomputador {

	private JFrame frame;
	Gerente ger = new Gerente();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principalcomputador window = new Principalcomputador();
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
	public Principalcomputador() {
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
		
		JButton btiniciar = new JButton("iniciar");
		btiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ger.setEncerrar(false);
				ger.acionar();
			}
		});
		btiniciar.setBounds(29, 87, 97, 25);
		frame.getContentPane().add(btiniciar);
		
		JButton btencerrar = new JButton("encerrar");
		btencerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ger.setEncerrar(true);
			}
		});
		btencerrar.setBounds(29, 186, 97, 25);
		frame.getContentPane().add(btencerrar);
	}
}
