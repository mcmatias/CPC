package controle;

import javax.swing.JTextField;

import modelo.*;
import rede.Cliente;
import rede.Servidor;

public class FluxoTanque extends Thread {
	
	Tanque T = new Tanque();
	JTextField textoMsgRec;
	JTextField textoMsgEnv;
	JTextField textoEstadoAtual;
	boolean fim = false;
	boolean concluirBanho = false;
				
	public boolean isConcluirBanho() {
		return concluirBanho;
	}

	public void setConcluirBanho(boolean concluirBanho) {
		this.concluirBanho = concluirBanho;
	}

	public boolean isFim() {
		return fim;
	}

	public void setFim(boolean fim) {
		this.fim = fim;
	}

	public JTextField getTextoMsgRec() {
		return textoMsgRec;
	}

	public void setTextoMsgRec(JTextField textoMsgRec) {
		this.textoMsgRec = textoMsgRec;
	}

	public JTextField getTextoMsgEnv() {
		return textoMsgEnv;
	}

	public void setTextoMsgEnv(JTextField textoMsgEnv) {
		this.textoMsgEnv = textoMsgEnv;
	}

	public JTextField getTextoEstadoAtual() {
		return textoEstadoAtual;
	}

	public void setTextoEstadoAtual(JTextField textoEstadoAtual) {
		this.textoEstadoAtual = textoEstadoAtual;
	}

	public void acionar ()
	{		
		Cliente cliEsteira = new Cliente();
		Servidor threadServ = new Servidor();		
	    threadServ.start();
	    boolean conectouAesteira = false;
		
	    cliEsteira.setIPhost("localhost");
	    cliEsteira.setPortaServidor(4444);
	    while (!conectouAesteira && !fim)
	    	{
	    		conectouAesteira = cliEsteira.conectarAoservidor();		
	    	}
		while (!fim)
		{
			//System.out.println("Estou executando tanque");
			T.executaTanque();
			textoEstadoAtual.setText(T.getEstado());
			if (T.isTransmitiu())
			{
				cliEsteira.enviarMensagem(T.getMensagem());
				textoMsgEnv.setText(T.getMensagem());
				T.setTransmitiu(false);
				T.setMensagem("nenhuma");
			}
			if (threadServ.isNovaMsg())
			{
				T.setMensagem(threadServ.getTextoDeEntrada());
				textoMsgRec.setText(threadServ.getTextoDeEntrada());
				threadServ.setNovaMsg(false);
			}
			else T.setMensagem("nenhuma");
			if (concluirBanho == true) 
			{
				T.setBanhoConcluido(true);
				concluirBanho = false;
			}
		}	
		if (conectouAesteira) 
		{
			cliEsteira.encerraConexao();
			threadServ.setListening(false);
		}
	}
	
	public void run ()
	{
		acionar ();
	}	
	
}
