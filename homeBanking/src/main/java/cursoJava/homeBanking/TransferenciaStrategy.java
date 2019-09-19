package cursoJava.homeBanking;

public interface TransferenciaStrategy {
	
	public void execute(Conta contaOrigem, Conta contaDestino, double valor);

}
