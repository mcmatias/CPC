/*package controle;

import modelo.*;
import rede.*;
import javax.swing.JTextField;

public class FluxoEsteiraA extends Thread {
	
	EsteiraA EA = new EsteiraA();
	JTextField campoEsteira;
	JTextField textoMsgRec;
	JTextField textoMsgEnv;	
		
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

	public FluxoEsteiraA (JTextField campoEsteira)
	{
		this.campoEsteira = campoEsteira;
	}
	
	public void acionar ()
	{
		Cliente clienteCarrinho = new Cliente();		
	    boolean conectou = false;
	    clienteCarrinho.setIPhost("localhost");
	    clienteCarrinho.setPortaServidor(5555);
	    while (!conectou)
    	{
    		conectou = clienteCarrinho.conectarAoservidor();		
    	}	    

		while (conectou)
		{
			//System.out.println("[ESTEIRA] - Estou acionando");
			EA.executaEsteira();
			if (EA.isEncerrarEsteira() == true)
			{
				conectou = false;
				clienteCarrinho.encerraConexao();
			}
			if (EA.isTransmitiuParaElevador())
			{
				clienteCarrinho.enviarMensagem(EA.getMensagem());
				textoMsgEnv.setText(EA.getMensagem());
				EA.setTransmitiuParaElevador(false);
				EA.setMensagem("nenhuma");
			}
			if (EA.isRecebeuMsgCarrinho())//NOTA quem ta setando isso aqui?!?!!? to setando dentro da esteiraA no estado correspondente
			{
				clienteCarrinho.receberMensagem();
				textoMsgRec.setText(EA.getMensagem());
				EA.setRecebeuMsgCarrinho(true); //acredito que isso aqui tenha que ser setado la no Carrinho quando ele estiver mandando msg p ca
				EA.setMensagem("nenhuma");
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
*/

package controle;
import modelo.*;
import rede.*;

import javax.swing.JTextField;
public class FluxoEsteiraA extends Thread {
	EsteiraA EA = new EsteiraA();
	JTextField textoMsgRec;
	JTextField textoMsgEnv;
	boolean encerrar=false;
	
		
	public boolean isEncerrar() {
		return encerrar;
	}

	public void setEncerrar(boolean encerrar) {
		this.encerrar = encerrar;
		EA.setEncerrarEsteira(encerrar);
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
		Cliente clienteCarrinho = new Cliente();	
	    boolean conectou = false;		
	    clienteCarrinho.setIPhost("localhost");
	    clienteCarrinho.setPortaServidor(5555);
	    String msgRecebida = "nenhuma";
	    String msgAnterior = "nenhuma";
	    while (!conectou)
    	{
    		conectou = clienteCarrinho.conectarAoservidor();		
    	}
	    clienteCarrinho.start();
		while (conectou)
		{
			//System.out.println("[CAIXA] - Estou acionando");			
			EA.executaEsteira();
			if (EA.isEncerrarEsteira() == true)
			{
				clienteCarrinho.encerraConexao();
				conectou = false;
			}
			if (EA.isTransmitiuParaElevador())
			{
				clienteCarrinho.enviarMensagem(EA.getMensagem());
				textoMsgEnv.setText(EA.getMensagem());
				EA.setTransmitiuParaElevador(false);
				EA.setMensagem("nenhuma");
			}
			msgRecebida = clienteCarrinho.getInputLine(); 
			if (!msgRecebida.equals(msgAnterior))
			{
				EA.setMensagem(msgRecebida);
				System.out.println("mensagem: " + EA.getMensagem());
				EA.setRecebeuMsgCarrinho(true);
				msgAnterior = msgRecebida;
/*				clienteCarrinho.receberMensagem();*/ //orlando nao esta usando esse comando

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
