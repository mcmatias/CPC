package modelo;

public class EsteiraB {

	static enum tipoEstado {Move_EsteiraB, Repouso_EsteiraB};
	
	boolean transmitiuParaElevador = false;
	boolean encerrarEsteira = false;
	tipoEstado estado = tipoEstado.Repouso_EsteiraB;
	String mensagem = "nenhuma";
	boolean recebeuMsgCarrinho = false;
	
	
	
	public boolean isTransmitiuParaElevador() {
		return transmitiuParaElevador;
	}

	public void setTransmitiuParaElevador(boolean transmitiuParaElevador) {
		this.transmitiuParaElevador = transmitiuParaElevador;
	}

	public boolean isEncerrarEsteira() {
		return encerrarEsteira;
	}

	public void setEncerrarEsteira(boolean encerrarEsteira) {
		this.encerrarEsteira = encerrarEsteira;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public tipoEstado getEstado() {
		return estado;
	}

	public void setEstado(tipoEstado estado) {
		this.estado = estado;
	}
	public void transmiteParaElevador(String msg)
	{
		transmitiuParaElevador = true;
		mensagem = msg;
	}	
	//TODO - aqui imagino que vai ter um recebeDeElevador em que eu receba a msg que o elevador mandar
	public boolean isRecebeuMsgCarrinho() {
		return recebeuMsgCarrinho;
	}

	public void setRecebeuMsgCarrinho(boolean recebeuMsgCarrinho) {
		this.recebeuMsgCarrinho = recebeuMsgCarrinho;
	}
	
	public void executaEsteira() {
		
		  switch (estado)
		  {
		   	case Move_EsteiraB: 
		   		transmiteParaElevador("NovaPecaB");
		   		estado = tipoEstado.Repouso_EsteiraB;
		   		break;
		   	case Repouso_EsteiraB:
		   		if (mensagem.equals("LiberadoB")){
			   		recebeuMsgCarrinho = true;
			   		estado = tipoEstado.Move_EsteiraB;
		   		}
		   		break;		
		  }
	}


}
