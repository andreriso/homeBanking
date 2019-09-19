package cursoJava.homeBanking;

public class TaxaMensal implements ContaVisitor {
	
	@Override
	public void visit(Conta conta) {
        if (conta instanceof ContaCorrente) {
        	conta.debitar(1d);			
		} else {
			double saldo = conta.getSaldo();
			conta.debitar(saldo*0.01);
		}
	}

}
