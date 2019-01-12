/*package rede;

import java.net.*;
import java.io.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Servidor extends Thread {
    
	int porta = 4446;
	JLabel portaAtiva;
	boolean listening = true;
	boolean novaMsg = false;	
	JTextField textoDeEntrada;		
	boolean conectou = false;
	
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

	public JTextField getTextoDeEntrada() {
		return textoDeEntrada;
	}

	public void setTextoDeEntrada(JTextField textoDeEntrada) {
		this.textoDeEntrada = textoDeEntrada;
	}

	public JLabel getPortaAtiva() {
		return portaAtiva;
	}

	public void setPortaAtiva(JLabel portaAtiva) {
		this.portaAtiva = portaAtiva;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	
	public boolean isConectou() {
		return conectou;
	}

	public void setConectou(boolean conectou) {
		this.conectou = conectou;
	}

	public void run() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("[ESTEIRA] - Serviço Ativado na Porta " + porta);
            //portaAtiva.setText("Serviço Ativado na Porta " + porta);
        } catch (IOException e) {
            System.err.println("[ESTEIRA] - Não pude ouvir na porta " + porta);
            System.exit(1);
        }

        Socket clientSocket = null;
        String inputLine;
        //PrintStream out;
        DataInputStream in = null;
        
        try {
            clientSocket = serverSocket.accept();
            conectou = true;
        } catch (IOException e) {
            System.err.println("[ESTEIRA] - Conexão com cliente falhou.");
            //System.exit(1);
        }
        try {
        	
         out = new PrintStream(
                          new BufferedOutputStream(
			      clientSocket.getOutputStream(), 1024), false); 
        in = new DataInputStream(clientSocket.getInputStream());
        while (listening) {
            inputLine = in.readUTF();                      
            if (!inputLine.equals(textoDeEntrada.getText())) 
        	{
            	novaMsg = true;
            	textoDeEntrada.setText(inputLine);
            	System.out.println("[ESTEIRA] - Recebida a mensagem: " + inputLine);
        	} 
            else novaMsg = false;             
       }
       //out.close();
       in.close();
       clientSocket.close();
       serverSocket.close();
        
        }
        catch (IOException e) {
            System.err.println("[ESTEIRA] - Conexão com cliente falhou.");
            //System.exit(1);
        }
                
    }
}
*/
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
            	System.out.println("[ELEVADOR] - Recebida a mensagem: " + inputLine);
        	} 
            else novaMsg = false;             
       }                
       //out.close();
       in.close();
       clientSocket.close();
        }
        catch (IOException e) {
            System.err.println("[ELEVADOR] - Conexão com cliente falhou.");
            //System.exit(1);
        }
                
    }
}
