package cursoJava.homeBanking;

import java.util.concurrent.ExecutionException;

import cursoJava.homeBanking.Movimentacao.TipoMovimentacao;

public class Transferencia {

	private TransferenciaStrategy transferenciaStrategy;

	public void execute(Conta contaOrigem, Conta contaDestino, double valor) {
		if (contaOrigem.getCliente().equals(contaDestino.getCliente())) {
			// sem taxa
			transferenciaStrategy = new TransferenciaSemTaxa();
		} else {
			transferenciaStrategy = new TransferenciaComTaxa();
		}

		COAF coaf = COAF.getInstance();
		coaf.registerObserver((Movimentacao m) -> {
			if (m.getValor() > 50000 && m.getTipoMovimentacao().equals(TipoMovimentacao.CREDITO)) {
				try {
					COAFService.notificar(m.getInfo());
					System.out.println("COAF NOTIFICADO");
				} catch (ExecutionException | InterruptedException e) {
					System.out.println("Erro ao notificar COAF");
					e.printStackTrace();
				}	
			}
		});
				
		contaOrigem.debitar(valor);		
		coaf.notifyObservers(contaDestino.creditar(valor));
		transferenciaStrategy.execute(contaOrigem, contaDestino, valor);
	}
}
