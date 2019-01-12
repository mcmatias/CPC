package modelo;

public class Caixa {
	static enum tipoEstado {repouso,num_conta,central};
	
	tipoEstado estado=tipoEstado.repouso;

	boolean encerrarCaixa=false;
	boolean transmitiuParaComputador=false;
	String operacao="nenhuma";
	String mensagem ="nenhuma";
	String corrente;
	String valor;
	int bit = 0;
	
	public void acessarconta(){
		
	}
	
	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public boolean isEncerrarCaixa() {
		return encerrarCaixa;
	}

	public void setEncerrarCaixa(boolean encerrarCaixa) {
		this.encerrarCaixa = encerrarCaixa;
	}
	public boolean isTransmitiuParaComputador() {
		return transmitiuParaComputador;
	}

	public void setTransmitiuParaComputador(boolean transmitiuParaComputador) {
		this.transmitiuParaComputador = transmitiuParaComputador;
	}

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCorrente() {
		return corrente;
	}

	public void setCorrente(String corrente) {
		this.corrente = corrente;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}



	public void transmiteParaComputador(String msg)
	{
		transmitiuParaComputador = true;
		mensagem = msg + "::" + bit;
		bit = (bit + 1) % 2;
	}	
	public void executaCaixa(){
		switch (estado){
		case repouso:
			System.out.println("[CAIXA]- Estou no estado repouso.");
			acessarconta();
			mensagem="nenhuma";
			estado=tipoEstado.num_conta;
			break;
		case num_conta:
			System.out.println("[CAiXA] - Estou no estado num_conta");
			if (operacao.equals("saque")){
				estado=tipoEstado.central;
				operacao="nenhuma";
				transmiteParaComputador("saque("+corrente+","+valor+")");
			}
			if (operacao.equals("deposito")){
				estado=tipoEstado.central;
				operacao="nenhuma";
				transmiteParaComputador("deposito("+corrente+","+valor+")");
			}
			break;
		case central:
			System.out.println("[CAIXA] - Estou no estado computador central");
			if (mensagem.contains("Confirmado")){
				estado=tipoEstado.repouso;
				mensagem="nenhuma";
			}
			if (mensagem.contains("SemFundos")){
				estado=tipoEstado.repouso;
				mensagem="nenhuma";
			}
			break;
				
		}
	}

}
