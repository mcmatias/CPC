package visao;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import controle.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PrincipalEsteira {

	private JFrame frame;
	private JTextField campo_esteira;
	private JTextField textoRecebido;
	private JTextField textoEnviado;

	/**
	 * Launch the application.
	 */
	
	//FluxoEsteira fe = new FluxoEsteira(); 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalEsteira window = new PrincipalEsteira();
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
	public PrincipalEsteira() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 319);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		campo_esteira = new JTextField();
		campo_esteira.setText("*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*");
		campo_esteira.setBounds(25, 47, 374, 20);
		frame.getContentPane().add(campo_esteira);
		campo_esteira.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);
		
		JMenuItem MenuItemIniciarE = new JMenuItem("Iniciar Esteira");
		MenuItemIniciarE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FluxoEsteira fe = new FluxoEsteira(campo_esteira);
				fe.setTextoMsgRec(textoRecebido);
				fe.setTextoMsgEnv(textoEnviado);
			    fe.start();			
				
				//fe.acionar(campo_esteira);
			}
		});
		menuArquivo.add(MenuItemIniciarE);
		
		JMenuItem MenuItemSair = new JMenuItem("Sair");
		MenuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		menuArquivo.add(MenuItemSair);
		
		JLabel MsgRec = new JLabel("Mensagem Recebida:");
		MsgRec.setBounds(25, 253, 129, 14);
		frame.getContentPane().add(MsgRec);
		
		textoRecebido = new JTextField();
		textoRecebido.setText("Nenhuma");
		textoRecebido.setBounds(164, 250, 93, 20);
		frame.getContentPane().add(textoRecebido);
		textoRecebido.setColumns(10);
		
		JLabel MsgEnv = new JLabel("Mensagem Enviada:");
		MsgEnv.setBounds(25, 225, 120, 14);
		frame.getContentPane().add(MsgEnv);
		
		textoEnviado = new JTextField();
		textoEnviado.setText("Nenhuma");
		textoEnviado.setColumns(10);
		textoEnviado.setBounds(164, 219, 93, 20);
		frame.getContentPane().add(textoEnviado);
	}
}
