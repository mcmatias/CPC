package rede;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Cliente {
	
	int portaServidor = 5555;
	String IPhost = "localhost";
	DataOutputStream out = null;
	Socket ClienteSocket = null;	
	
    public int getPortaServidor() {
		return portaServidor;
	}

	public void setPortaServidor(int portaServidor) {
		this.portaServidor = portaServidor;
	}

	public String getIPhost() {
		return IPhost;
	}

	public void setIPhost(String iPhost) {
		IPhost = iPhost;
	}

	public boolean conectarAoservidor() {
                
        //DataInputStream in = null;

        try {
            ClienteSocket = new Socket(IPhost, portaServidor);
            out = new DataOutputStream(ClienteSocket.getOutputStream());
            out.writeUTF("Conectado");
            System.out.println("[TANQUE] - Conectado");
            return true;           
        } catch (UnknownHostException e) {
            System.err.println("[TANQUE] - Host desconhecido: " + IPhost);            
	        System.exit(1);
        } catch (IOException e) {
            System.err.println("[TANQUE] - N�o pude me conectar � Esteira.");
            return false;
	        //System.exit(1);
        }
        return false; 
	}
	
	public void enviarMensagem(String msg) {

	  try {    
		out.writeUTF(msg);
	  }
	  catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),
		                  "Cliente", JOptionPane.INFORMATION_MESSAGE);
	  }
	}

	public void encerraConexao()
	{	
	try {
		out.close();
		ClienteSocket.close();
	}
	catch (IOException e) {
        System.err.println("N�o pude me conectar ao host: " + IPhost);
    }
}
}