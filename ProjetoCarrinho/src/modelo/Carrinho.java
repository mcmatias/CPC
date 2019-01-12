package modelo;

public class Carrinho {

	static enum tipoEstado {Deposita_Peca, Repouso_Carrinho, Vai_Posicao_Elevador, Espera_Resposta_Elevador, 
							Vai_PosicaoA, Vai_PosicaoB, Agarra_Peca_em_A, Agarra_Peca_em_B};
	
	boolean PosA = false;
	boolean PosB = false;
	tipoEstado estado = tipoEstado.Repouso_Carrinho;
	boolean transmitiuParaEsteiraA = false;
	boolean transmitiuParaEsteiraB = false;
	boolean transmitiuParaElevador = false;
	boolean recebeuMsgEsteiraA = false;
	boolean recebeuMsgEsteiraB = false;
	boolean recebeuMsgElevador = false;
	boolean encerrarCarrinho = false;
	int posicao = 0;
	String mensagem = "nenhuma";

	
	public boolean isRecebeuMsgEsteiraA() {
		return recebeuMsgEsteiraA;
	}

	public void setRecebeuMsgEsteiraA(boolean recebeuMsgEsteiraA) {
		this.recebeuMsgEsteiraA = recebeuMsgEsteiraA;
	}

	public boolean isRecebeuMsgEsteiraB() {
		return recebeuMsgEsteiraB;
	}

	public void setRecebeuMsgEsteiraB(boolean recebeuMsgEsteiraB) {
		this.recebeuMsgEsteiraB = recebeuMsgEsteiraB;
	}

	public boolean isRecebeuMsgElevador() {
		return recebeuMsgElevador;
	}

	public void setRecebeuMsgElevador(boolean recebeuMsgElevador) {
		this.recebeuMsgElevador = recebeuMsgElevador;
	}

	public boolean isPosA() {
		return PosA;
	}

	public void setPosA(boolean posA) {
		PosA = posA;
	}

	public boolean isPosB() {
		return PosB;
	}

	public void setPosB(boolean posB) {
		PosB = posB;
	}

	public tipoEstado getEstado() {
		return estado;
	}

	public void setEstado(tipoEstado estado) {
		this.estado = estado;
	}

	public boolean isEncerrarCarrinho() {
		return encerrarCarrinho;
	}

	public void setEncerrarCarrinho(boolean encerrarCarrinho) {
		this.encerrarCarrinho = encerrarCarrinho;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public boolean isTransmitiuParaEsteiraA() {
		return transmitiuParaEsteiraA;
	}
	public boolean isTransmitiuParaEsteiraB() {
		return transmitiuParaEsteiraB;
	}
	public boolean isTransmitiuParaElevador() {
		return transmitiuParaElevador;
	}
	public void setTransmitiuParaEsteiraA(boolean transmitiuParaEsteiraA) {
		this.transmitiuParaEsteiraA = transmitiuParaEsteiraA;
	}
	public void setTransmitiuParaEsteiraB(boolean transmitiuParaEsteiraB) {
		this.transmitiuParaEsteiraB = transmitiuParaEsteiraB;
	}
	public void setTransmitiuParaElevador(boolean transmitiuParaElevador) {
		this.transmitiuParaElevador = transmitiuParaElevador;
	}
	public void transmiteParaEsteiraA(String msg)
	{
		transmitiuParaEsteiraA = true;
		mensagem = msg;
	}	
	
	public void transmiteParaEsteiraB(String msg)
	{
		transmitiuParaEsteiraB = true;
		mensagem = msg;
	}
	public void transmiteParaElevador(String msg)
	{
		transmitiuParaElevador = true;
		mensagem = msg;
	}
/*	public void recebeMsgEsteteiraA(String msg)
	{
		recebeuMsgEsteiraA = true;
		mensagem = msg;
	}
	public void recebeMsgEsteiraB(String msg)
	{
		recebeuMsgEsteiraB = true;
		mensagem = msg;
	}
	public void recebeMsgElevador(String msg)
	{
		recebeuMsgElevador = true;
		mensagem = msg;
	}*/
	
	public void executarCarrinho() {
		
		  switch (estado)
		  {
		   	case Agarra_Peca_em_A: 
		   		estado = tipoEstado.Vai_Posicao_Elevador;
		   		transmiteParaEsteiraA("LiberadoA");
		   		break;
		   	case Agarra_Peca_em_B:
		   		estado = tipoEstado.Vai_Posicao_Elevador;
		   		transmiteParaEsteiraB("LiberadoB");
		   		break;
		   	case Deposita_Peca:	
		   		if (mensagem.equals("Sim"))
		   		{		   			
		   			mensagem = "nenhuma";		   				
		   			transmiteParaElevador("Liberado");
			   		estado = tipoEstado.Repouso_Carrinho;
		   		}		   		
		   		break;
		   	case Espera_Resposta_Elevador:
		   		//transmiteParaElevador("JaEstaNoAlto");
	   			while (mensagem.equals("Nao")) // tE6
	   			{
	   				transmiteParaElevador("JaEstaNoAlto");		
	   			} 
	   			estado = tipoEstado.Deposita_Peca;
		   		break;
		   	case Repouso_Carrinho:
	   			if (mensagem.equals("NovaPecaA")) // tE6
	   			{
	   				estado = tipoEstado.Vai_PosicaoA;
	   			} else if (mensagem.equals("NovaPecaB")) {
	   				estado = tipoEstado.Vai_PosicaoB;
	   			}
		   		break;		  
		   	case Vai_Posicao_Elevador:
		   		transmiteParaElevador("JaEstaNoAlto");
		   		estado = tipoEstado.Espera_Resposta_Elevador;
		   		break;
		   	case Vai_PosicaoA:
	   			PosA = true;
	   			estado = tipoEstado.Agarra_Peca_em_A;
		   		break;
		   	case Vai_PosicaoB:
	   			PosA = true;
	   			estado = tipoEstado.Agarra_Peca_em_A;
		   		break;
		  }
	}
}
