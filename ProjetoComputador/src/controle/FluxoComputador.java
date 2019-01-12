package controle;

import rede.*;
import modelo.*;

public class FluxoComputador extends Thread {
	boolean encerrar=false;
	Computador CO = new Computador();
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
        	CO.executaComputador();                            		    				
			if (CO.isTransmitiuParaCaixa())
			{
				serv.enviarMensagem(CO.getMensagem());
				//textoMsgEnv.setText(CO.getMensagem());
				System.out.println(CO.getMensagem());
				CO.setTransmitiuParaCaixa(false);
				CO.setMensagem("nenhuma");
			}
			if (serv.isNovaMsg() == true)
			{
				CO.setMensagem(serv.getTextoDeEntrada());
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
