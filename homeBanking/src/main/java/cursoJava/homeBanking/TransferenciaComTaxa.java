package cursoJava.homeBanking;

public class TransferenciaComTaxa implements TransferenciaStrategy {

	@Override
	public void execute(Conta contaOrigem, Conta contaDestino, double valor) {
		contaOrigem.debitar(10D);
	}

}
