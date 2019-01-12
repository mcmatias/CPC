package modelo;

import javax.swing.JOptionPane;


public class Computador {
	
	static enum tipoEstado {analisar_saldo,inicio};
	tipoEstado estado=tipoEstado.inicio;
	boolean transmitiuParaCaixa=false;
	String operacao="nenhuma";
	String mensagem ="nenhuma";
	String corrente;
	double saldo=0;
	double valor;
	String valorstring = "";
	int bit = 0;
	
	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public boolean isTransmitiuParaCaixa() {
		return transmitiuParaCaixa;
	}

	public void setTransmitiuParaCaixa(boolean transmitiuParaCaixa) {
		this.transmitiuParaCaixa = transmitiuParaCaixa;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void transmiteParaCaixa(String msg)
	{
		transmitiuParaCaixa = true;		
		mensagem = msg + "::" + bit;
		bit = (bit + 1) % 2;
	}	
	
	public void executaComputador(){
		switch (estado){
			case inicio:
				//System.out.println("[COMPUTADOR] - Estou no estado inicio");
				if (mensagem.contains("saque")){
					estado=tipoEstado.analisar_saldo;
					
				}
				if (mensagem.contains("deposito")){
					valorstring = mensagem.substring(mensagem.indexOf(",")+1);
					valorstring = valorstring.substring(0,valorstring.length()-4);
					valor = Double.parseDouble(valorstring);
					saldo=saldo+valor;
					JOptionPane.showMessageDialog(null, "Saldo = "+ saldo);
					mensagem="nenhuma";
					transmiteParaCaixa("Confirmado");					
					estado=tipoEstado.inicio;
				}
				break;
			case analisar_saldo:
				//System.out.println("[COMPUTADOR] - Estou no estado analisar saldo");
				valorstring = mensagem.substring(mensagem.indexOf(",")+1);
				valorstring = valorstring.substring(0,valorstring.length()-4);
				valor = Double.parseDouble(valorstring);
				mensagem="nenhuma";
				if (saldo>=valor){
					saldo=saldo-valor;
					JOptionPane.showMessageDialog(null, "Saldo = "+ saldo);
					transmiteParaCaixa("Confirmado");
					estado=tipoEstado.inicio;
				}
				else{
					transmiteParaCaixa("SemFundos");
					estado=tipoEstado.inicio;
				}
				break;
		}
			
	}
}
