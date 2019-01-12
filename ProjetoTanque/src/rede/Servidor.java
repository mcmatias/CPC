package rede;

import java.net.*;
import java.io.*;

public class Servidor extends Thread {
    
	int porta = 4445;
	//JLabel portaAtiva;
	boolean listening = true;
	boolean novaMsg = false;
	String textoDeEntrada = "nenhuma";	
	
	public boolean isNovaMsg() {
		return novaMsg;
	}

	public void setNovaMsg(boolean novaMsg) {
		this.novaMsg = novaMsg;
	}

	public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	public String getTextoDeEntrada() {
		return textoDeEntrada;
	}

	public void setTextoDeEntrada(String textoDeEntrada) {
		this.textoDeEntrada = textoDeEntrada;
	}
/*
	public JLabel getPortaAtiva() {
		return portaAtiva;
	}

	public void setPortaAtiva(JLabel portaAtiva) {
		this.portaAtiva = portaAtiva;
	}
*/
	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	public void run() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("[TANQUE] - Serviço Ativado na Porta " + porta);
            //portaAtiva.setText("Serviço Ativado na Porta " + porta);
        } catch (IOException e) {
            System.err.println("Não pude ouvir na porta " + porta);
            System.exit(1);
        }

        Socket clientSocket = null;
        String inputLine;
        //PrintStream out;
        DataInputStream in = null;
        
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("[TANQUE] - Conexão com cliente falhou.");
            System.exit(1);
        }
        try {
        	/*
         out = new PrintStream(
                          new BufferedOutputStream(
			      clientSocket.getOutputStream(), 1024), false); */
        in = new DataInputStream(clientSocket.getInputStream());
        while (listening) {
            inputLine = in.readUTF();
            if (!inputLine.equals(textoDeEntrada)) 
        	{
            	novaMsg = true;
            	textoDeEntrada = inputLine;
            	System.out.println("[TANQUE] - Recebida a mensagem: " + inputLine);
        	} 
            else novaMsg = false;             
       }
       //out.close();
       in.close();
       clientSocket.close();
       serverSocket.close();
        
        }
        catch (IOException e) {
            System.err.println("Conexão com cliente falhou.");
            System.exit(1);
        }
                
    }
}
