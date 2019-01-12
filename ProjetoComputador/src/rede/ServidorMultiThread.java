package rede;

import java.net.*;
import java.io.*;
import controle.FluxoComputador;

public class ServidorMultiThread extends Thread {
	
	int porta = 5555;
	boolean listening = true;
	FluxoComputador[] fc = new FluxoComputador[5];
	
	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
	public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	//Método responsável por aguardar a conexão com o nodo, criando um processo servidor para 
	//atender a cada nodo.
	public void iniciar () throws IOException {
		
		ServerSocket serverSocket = null;
		
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("[COMPUTADOR] - Serviço Ativado na Porta " + porta);
            //portaAtiva.setText("Serviço Ativado na Porta " + porta);
        } catch (IOException e) {
            System.err.println("[COMPUTADOR] - Não pude ouvir na porta " + porta);
            System.exit(1);
        }
	        int i = 0;
        	while (listening)
		        { 
	        	   Servidor serv = new Servidor(serverSocket.accept());	        	   
	        	   serv.start();
	        	   fc[i] = new FluxoComputador();
	        	   fc[i].setServ(serv);
	        	   fc[i].start();
	        	   System.out.println("Conectado ao cliente: " + i);
	        	   i++;
		        }

	        serverSocket.close();
	    }

	public void run ()
	{
		  try {	
			     iniciar();
		  }
		  catch (IOException e) {
		      System.err.println("[COMPUTADOR] - Não pude ouvir na porta " + porta); }
	}
	

}
