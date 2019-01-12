package rede;


import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Servidor extends Thread {
    
	boolean novaMsg = false;	
	String textoDeEntrada = "nenhuma";	
	Socket clientSocket = null;
	boolean listening = true;
	DataInputStream in = null;
    DataOutputStream out = null;
    
    public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	public Servidor(Socket socket) {
    	clientSocket = socket; 
    }
	
	public boolean isNovaMsg() {
		return novaMsg;
	}

	public void setNovaMsg(boolean novaMsg) {
		this.novaMsg = novaMsg;
	}

	public String getTextoDeEntrada() {
		return textoDeEntrada;
	}

	public void setTextoDeEntrada(String textoDeEntrada) {
		this.textoDeEntrada = textoDeEntrada;
	}
	
	public void enviarMensagem(String msg) {

		  try {    
			out.writeUTF(msg);		
		  }
		  catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(),
			                  "Servidor", JOptionPane.INFORMATION_MESSAGE);
		  }
		}
	
	public void run() {
        
        String inputLine;
        
        try {        	
        textoDeEntrada = "nenhuma";
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());        
        while (listening) {
            inputLine = in.readUTF();                      
            if (!inputLine.equals(textoDeEntrada)) 
        	{
            	novaMsg = true;
            	textoDeEntrada = inputLine;
            	System.out.println("[COMPUTADOR] - Recebida a mensagem: " + inputLine);
        	} 
            else novaMsg = false;             
       }                
       //out.close();
       in.close();
       clientSocket.close();
        }
        catch (IOException e) {
            System.err.println("[COMPUTADOR] - Conexão com cliente falhou.");
            //System.exit(1);
        }
                
    }
}
