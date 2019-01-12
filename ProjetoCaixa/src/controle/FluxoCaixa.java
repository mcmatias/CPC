package controle;
import modelo.*;
import rede.*;

import javax.swing.JTextField;
public class FluxoCaixa extends Thread {
	Caixa CA = new Caixa();
	JTextField textoMsgRec;
	JTextField textoMsgEnv;
	String operacao="nenhuma";
	JTextField CampoConta;
	JTextField CampoValor;
	boolean encerrar=false;
	
		
	public boolean isEncerrar() {
		return encerrar;
	}

	public void setEncerrar(boolean encerrar) {
		this.encerrar = encerrar;
		CA.setEncerrarCaixa(encerrar);
	}

	public JTextField getCampoConta() {
		return CampoConta;
	}

	public void setCampoConta(JTextField campoConta) {
		CampoConta = campoConta;
		CA.setCorrente(CampoConta.getText());
	}

	public JTextField getCampoValor() {
		return CampoValor;
		
	}

	public void setCampoValor(JTextField campoValor) {
		CampoValor = campoValor;
		CA.setValor(CampoValor.getText());
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
		CA.setOperacao(operacao);
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
	
	public void acionar ()
	{
		Cliente cliComputador = new Cliente();		
	    boolean conectou = false;		
	    cliComputador.setIPhost("localhost");
	    cliComputador.setPortaServidor(5555);
	    String msgRecebida = "nenhuma";
	    String msgAnterior = "nenhuma";
	    while (!conectou)
    	{
    		conectou = cliComputador.conectarAoservidor();		
    	}
	    cliComputador.start();
		while (conectou)
		{
			//System.out.println("[CAIXA] - Estou acionando");			
			CA.executaCaixa();
			if (CA.isEncerrarCaixa() == true)
			{
				cliComputador.encerraConexao();
				conectou = false;
			}
			if (CA.isTransmitiuParaComputador())
			{
				cliComputador.enviarMensagem(CA.getMensagem());
				//textoMsgEnv.setText(CA.getMensagem());
				System.out.println(CA.getMensagem());
				CA.setTransmitiuParaComputador(false);
				CA.setMensagem("nenhuma");
			}
			msgRecebida = cliComputador.getInputLine(); 
			if (!msgRecebida.equals(msgAnterior))
			{
				CA.setMensagem(msgRecebida);
				System.out.println("mensagem: " + CA.getMensagem());
				msgAnterior = msgRecebida;
			}
			try 
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
		}		
	}
	
	public void run ()
	{
		acionar ();
	}
}
