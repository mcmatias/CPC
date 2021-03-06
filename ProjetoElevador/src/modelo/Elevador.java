package modelo;

import modelo.Elevador.tipoEstado;

public class Elevador {

	static enum tipoEstado {Elevador_em_alto, Elevador_Desce};
	
	boolean elevadorCarregado = false;
	boolean encerrarEsteira = false;
	boolean transmitiuParaCarrinho = false;
	tipoEstado estado = tipoEstado.Elevador_em_alto;
	int posicao = 1;
	String mensagem = "nenhuma";
	


	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public boolean isEncerrarElevador() {
		return encerrarEsteira;
	}
	
	public boolean isTransmitiuParaCarrinho() {
		return transmitiuParaCarrinho;
	}
	
	public void setTransmitiuParaCarrinho(boolean transmitiuParaCarrinho) {
		this.transmitiuParaCarrinho = transmitiuParaCarrinho;
	}
	
	public void transmiteParaCarrinho(String msg)
	{
		transmitiuParaCarrinho = true;
		mensagem = msg;
	}	

	public void executarElevador() {
		
		  switch (estado)
		  {
		   	case Elevador_em_alto: 
		   		if (mensagem.equals("JaEstaNoAlto")){//preciso saber como receber essa mensagem la no FluxoElevador
		   			transmiteParaCarrinho("Sim");
//			   		estado = tipoEstado.E_Descendo;
		   		}
		   		if (mensagem.equals("Liberado")){//preciso saber como receber essa mensagem la no FluxoElevador
			   		estado = tipoEstado.Elevador_Desce;
		   		} 
		   		break;
		   	case Elevador_Desce:
		   		if (mensagem.equals("JaEstaNoAlto")){//preciso saber como receber essa mensagem la no FluxoElevador
		   			transmiteParaCarrinho("Nao");
			   		estado = tipoEstado.Elevador_em_alto;
		   		}
		   		break;
		  }
	}
	
}
