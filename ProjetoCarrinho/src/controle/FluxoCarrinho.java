package controle;

import modelo.*;
import rede.*;

import javax.swing.JTextField;

public class FluxoCarrinho extends Thread {
	
	Carrinho C = new Carrinho();
	JTextField campoCarrinho;
	String textoMsgRec;
	JTextField textoMsgEnv;	
		


	public Carrinho getC() {
		return C;
	}

	public void setC(Carrinho c) {
		C = c;
	}

	public JTextField getCampoCarrinho() {
		return campoCarrinho;
	}

	public void setCampoCarrinho(JTextField campoCarrinho) {
		this.campoCarrinho = campoCarrinho;
	}

	public String getTextoMsgRec() {
		return textoMsgRec;
	}

	public void setTextoMsgRec(String textoMsgRec) {
		this.textoMsgRec = textoMsgRec;
	}

	public JTextField getTextoMsgEnv() {
		return textoMsgEnv;
	}

	public void setTextoMsgEnv(JTextField textoMsgEnv) {
		this.textoMsgEnv = textoMsgEnv;
	}

	public FluxoCarrinho (JTextField campoCarrinho)
	{
		this.campoCarrinho = campoCarrinho;
	}
	
	public void acionar ()
	{
		Cliente cliElevador = new Cliente();
	    cliElevador.setIPhost("localhost");
	    cliElevador.setPortaServidor(4446);
		
	    String msgRecebida = "nenhuma";
	    String msgAnterior = "nenhuma";
	    
		Servidor esteiraA = new Servidor(null);
		esteiraA.setTextoDeEntrada(textoMsgRec);
		//esteiraA.setPorta(4444);//esse servidor novo nao tem mais porta?
	    esteiraA.start();
		Servidor esteiraB = new Servidor(null);
		esteiraB.setTextoDeEntrada(textoMsgRec);
		//esteiraB.setPorta(4445);
	    esteiraB.start();

	    boolean conectou = false;
		boolean todosConectados = false;

	    // DUVIDA - esse while ta c clienteTanque só, eu que tenho 3 clientes, coloco os 3 ali dentro?
	    while (!conectou)
    	{
    		conectou = cliElevador.conectarAoservidor();		
    	}	
	    
	    if (esteiraA.isConectou()
		    	&& esteiraB.isConectou()
		    	&& conectou){
	    	todosConectados = true;
	    }
	    
		while (todosConectados)
		{
			//System.out.println("[ESTEIRA] - Estou acionando");
			C.executarCarrinho();
			if (C.isEncerrarCarrinho() == true)
			{
				esteiraA.setListening(false); // DICA todo lugar que tiver thread servidor, tenho que fazer p esteiraA e p B
				esteiraA.setConectou(false);
				esteiraB.setListening(false);
				esteiraB.setConectou(false);
				conectou = false;
				cliElevador.encerraConexao();
			}
			if (C.isTransmitiuParaElevador())
			{
				cliElevador.enviarMensagem(C.getMensagem());
				textoMsgEnv.setText(C.getMensagem());
				C.setTransmitiuParaElevador(false);
				C.setMensagem("nenhuma");
			}
			if (C.isTransmitiuParaEsteiraA())
			{
				esteiraA.enviarMensagem(C.getMensagem()); //DICA tem que pegar o codigo novo em que o servidor envia msg e o cliente recebe
				textoMsgEnv.setText(C.getMensagem());
				C.setTransmitiuParaEsteiraA(false);
				C.setMensagem("nenhuma");//NOTA - AONDE AQUI QUE EU TO CONECTANDO C A ESTEIRA A?
				//AQUI EU DEVERIA SETAR EA.RECEBEUMSGCARRINHO = TRUE. ISSO AQUI EU TO LIGANDO C FluxoEsteiraA linha 67
			}
			if (C.isTransmitiuParaEsteiraB())
			{
				esteiraA.enviarMensagem(C.getMensagem());
				textoMsgEnv.setText(C.getMensagem());
				C.setTransmitiuParaEsteiraB(false);
				C.setMensagem("nenhuma");
			}
			if (esteiraA.isNovaMsg()){
				C.setMensagem(esteiraA.getTextoDeEntrada());
				esteiraA.setNovaMsg(false);
			} else {
				C.setMensagem("nenhuma");
			}
			if (esteiraB.isNovaMsg()){
				C.setMensagem(esteiraB.getTextoDeEntrada());
				esteiraB.setNovaMsg(false);
			} 
			msgRecebida = cliElevador.getInputLine(); 
			if (!msgRecebida.equals(msgAnterior))
			{
				C.setMensagem(msgRecebida);
				System.out.println("mensagem: " + C.getMensagem());
				msgAnterior = msgRecebida;
			} else {
				C.setMensagem("nenhuma");
			}
			
			try 
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e) {}
		}		
	}
	
	public void run ()
	{
		acionar ();
	}	

}
