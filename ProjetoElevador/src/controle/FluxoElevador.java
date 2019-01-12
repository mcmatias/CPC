/*package controle;

import modelo.*;
import rede.*;
import javax.swing.JTextField;

public class FluxoElevador extends Thread {
	
	Elevador E = new Elevador();
	JTextField posicaoElevador;
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

	public FluxoElevador (JTextField posicaoElevador)
	{
		this.posicaoElevador = posicaoElevador;
	}
	
	public void acionar()
	{
			
		Servidor servidorElevador = new Servidor();
		servidorElevador.setTextoDeEntrada(textoMsgRec);
		servidorElevador.start();
	    
		
	  
    
		String posAsterisco = "*";
		posAsterisco.setText(posAsterisco);
		while (servidorElevador.isConectou())
		{
			E.executarElevador();
			if (E.isEncerrarElevador() == true)
			{
				servidorElevador.setListening(false);
				servidorElevador.setConectou(false);
			}
			if (E.isTransmitiuParaCarrinho())
			{
				clienteCarrinho.enviarMensagem(E.getMensagem());
				textoMsgEnv.setText(E.getMensagem());
				E.setTransmitiuParaCarrinho(false);
				E.setMensagem("nenhuma");
			}
			if (servidorElevador.isNovaMsg())
			{
				E.setMensagem(servidorElevador.getTextoDeEntrada().getText());
				servidorElevador.setNovaMsg(false);
			}
			else E.setMensagem("nenhuma");
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

import rede.*;
import modelo.*;

public class FluxoElevador extends Thread {
	boolean encerrar=false;
	Elevador E = new Elevador();
	Servidor serv = null;
				
	public Servidor getServ() {
		return serv;
	}

	public void setServ(Servidor serv) {
		this.serv = serv;
	}

	public boolean isEncerrar() {
		return encerrar;
	}

	public void setEncerrar(boolean encerrar) {
		this.encerrar = encerrar;
	}

	public void acionar ()
	{		
        while (!encerrar) {
        	E.executarElevador();                            		    				
			if (E.isTransmitiuParaCarrinho())
			{
				serv.enviarMensagem(E.getMensagem());
				//textoMsgEnv.setText(CO.getMensagem());
				System.out.println(E.getMensagem());
				E.setTransmitiuParaCarrinho(false);
				E.setMensagem("nenhuma");
			}
			if (serv.isNovaMsg() == true)
			{
				E.setMensagem(serv.getTextoDeEntrada());
				serv.setNovaMsg(false);
			}
			//else CO.setMensagem("nenhuma");
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
