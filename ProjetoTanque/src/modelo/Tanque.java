package modelo;

import javax.swing.JOptionPane;

public class Tanque {
	
	static enum tipoEstado {T_Repouso, T_Banhando};
	
	boolean transmitiu = false;
	boolean mostrei = false;
	tipoEstado estado = tipoEstado.T_Repouso;
	String mensagem = "nenhuma";
	boolean banhoConcluido = false;
	
	public boolean isBanhoConcluido() {
		return banhoConcluido;
	}
	public void setBanhoConcluido(boolean banhoConcluido) {
		this.banhoConcluido = banhoConcluido;
	}
	public String getEstado() {
		return estado.toString();
	}
	public void setEstado(tipoEstado estado) {
		this.estado = estado;
	}
	public boolean isTransmitiu() {
		return transmitiu;
	}
	public void setTransmitiu(boolean transmitiu) {
		this.transmitiu = transmitiu;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public void acionarBanho()
	{
		System.out.println("Banho iniciado.");
	}
	
	public void transmite (String msg)
	{
		transmitiu = true;
		mensagem = msg;
	}	
	
	public void executaTanque() {
		
		  switch (estado)
		  {
		   	case T_Repouso:
		   		if (!mostrei) 
	   			{
		   			System.out.println("[TANQUE] - Estou no estado Repouso.");
		   			mostrei = true;
	   			}
		   		if (mensagem.equals("IniciarBanho"))
		   		{
		   			acionarBanho(); // tT2
		   			mensagem = "nenhuma";
			   		estado = tipoEstado.T_Banhando;
			   		mostrei = false;
		   		}		   		
		   		break;
		   	case T_Banhando:
		   		if (!mostrei) 
	   			{
		   			System.out.println("[TANQUE] - Estou no estado Banhando.");
		   			mostrei = true;
	   			}
				/*
				JOptionPane.showMessageDialog(null,"[TANQUE] - Estou no estado Banhando.",
		                  "TANQUE", JOptionPane.INFORMATION_MESSAGE); */
		   		
				if (banhoConcluido)
		   		{
					transmite ("FimBanho");
					estado = tipoEstado.T_Repouso;
			   		mostrei = false;
			   		banhoConcluido = false;
		   		}		   		
		   		break;		   		
		  }
	}	

}
