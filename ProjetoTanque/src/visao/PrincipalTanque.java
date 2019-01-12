package visao;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import controle.FluxoTanque;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class PrincipalTanque {

	private JFrame frame;
	private JTextField msgEnv;
	private JTextField msgRec;
	private JTextField estadoAtual;
	FluxoTanque ft = new FluxoTanque();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalTanque window = new PrincipalTanque();
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
	public PrincipalTanque() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 282);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		msgEnv = new JTextField();
		msgEnv.setBounds(10, 45, 214, 20);
		frame.getContentPane().add(msgEnv);
		msgEnv.setColumns(10);
		
		msgRec = new JTextField();
		msgRec.setBounds(10, 123, 214, 20);
		frame.getContentPane().add(msgRec);
		msgRec.setColumns(10);
		
		estadoAtual = new JTextField();
		estadoAtual.setBounds(10, 197, 214, 20);
		frame.getContentPane().add(estadoAtual);
		estadoAtual.setColumns(10);
		
		JLabel labelMsgEnv = new JLabel("Mensagem Enviada para a Esteira");
		labelMsgEnv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelMsgEnv.setBounds(10, 20, 214, 14);
		frame.getContentPane().add(labelMsgEnv);
		
		JLabel labelNsgRec = new JLabel("Mensagem Recebida da Esteira");
		labelNsgRec.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelNsgRec.setBounds(10, 98, 175, 14);
		frame.getContentPane().add(labelNsgRec);
		
		JLabel labelEstadoAtual = new JLabel("Estado Atual");
		labelEstadoAtual.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelEstadoAtual.setBounds(10, 172, 185, 14);
		frame.getContentPane().add(labelEstadoAtual);
		
		JButton jButtonIniciar = new JButton("Iniciar");
		jButtonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
											
				ft.setTextoMsgEnv(msgEnv);
				ft.setTextoMsgRec(msgRec);
				ft.setTextoEstadoAtual(estadoAtual);
				ft.start();
				
			}
		});
		jButtonIniciar.setBounds(286, 44, 139, 23);
		frame.getContentPane().add(jButtonIniciar);
		
		JButton jButtonEncerrar = new JButton("Encerrar");
		jButtonEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ft.setFim(true);
				
			}
		});
		jButtonEncerrar.setBounds(286, 163, 139, 23);
		frame.getContentPane().add(jButtonEncerrar);
		
		JButton jButtonFinalizarBanho = new JButton("Finalizar Banho");
		jButtonFinalizarBanho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ft.setConcluirBanho(true);
				
			}
		});
		jButtonFinalizarBanho.setBounds(286, 106, 139, 23);
		frame.getContentPane().add(jButtonFinalizarBanho);
	}
}
