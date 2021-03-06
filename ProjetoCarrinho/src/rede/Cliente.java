package rede;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Cliente extends Thread {

	int portaServidor = 5555;
	String IPhost = "localhost";
	DataOutputStream out = null;
	DataInputStream in = null;
	Socket ClienteSocket = null;
	String inputLine = "nenhuma";

	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}

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

		// DataInputStream in = null;

		try {
			ClienteSocket = new Socket(IPhost, portaServidor);
			out = new DataOutputStream(ClienteSocket.getOutputStream());
			out.writeUTF("Conectado");
			in = new DataInputStream(ClienteSocket.getInputStream());
			System.out.println("[CAIXA] - Conectado");
			return true;
		} catch (UnknownHostException e) {
			System.err.println("[CAIXA] - Host desconhecido: " + IPhost);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("[CAIXA] - N�o pude me conectar ao host: "
					+ IPhost);
			return false;
			// System.exit(1);
		}
		return false;
	}

	public void enviarMensagem(String msg) {

		try {
			out.writeUTF(msg);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Cliente",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void receberMensagem() {

		try {
			inputLine = in.readUTF();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Cliente",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void encerraConexao() {
		try {
			out.close();
			ClienteSocket.close();
		} catch (IOException e) {
			System.err.println("[CAIXA] - N�o pude me conectar ao host: "
					+ IPhost);
		}
	}

	public void run() {
		while (true)
			receberMensagem();
	}

}