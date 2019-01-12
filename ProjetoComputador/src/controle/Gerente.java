package controle;

import rede.ServidorMultiThread;

public class Gerente {
	
	boolean encerrar=false;
	
	public boolean isEncerrar() {
		return encerrar;
	}

	public void setEncerrar(boolean encerrar) {
		this.encerrar = encerrar;
	}	
	
	public void acionar ()
	{		
		ServidorMultiThread threadServ = new ServidorMultiThread();
	    threadServ.start();		
	}	

}
