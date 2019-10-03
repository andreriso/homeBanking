package cursoJava.homeBanking;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class App {

	public static void main(String[] args) {
		Banco banco = new Banco(1);

		List<Cliente> clientes = Arrays.asList(new Cliente.Builder(banco, "Cliente A", "Silva").set(c -> {
			c.endereco = "Rua 123";
			c.telefone = "9999-9999";
			c.contas.add(new ContaCorrente.Builder(1, 10000d).build());
		}).build(), new Cliente.Builder(banco, "Cliente B", "Souza").set(c -> {
			c.endereco = "Rua 88A";
			c.telefone = "1111-1111";
			c.contas.add(new ContaCorrente.Builder(2, 50000d).build());
		}).build());

		Optional<Cliente> optionalClienteA = clientes.stream()
				.filter(c -> c.getNome().equals("Cliente A") && c.getSobrenome().equals("Silva")).findFirst();
		if (optionalClienteA.isPresent()) {
			Cliente clienteA = optionalClienteA.get();
			ContaInvestimento ci1 = new ContaInvestimento.Builder(3, clienteA, TipoAplicacao.POUPANCA).build();
			ContaCorrente cc1 = (ContaCorrente) clienteA.getContas().get(0);

			Transferencia t1 = new Transferencia();
			t1.execute(cc1, ci1, 50001);
			cc1.gerarExtrato();
			ci1.gerarExtrato();

		}

		TaxaMensal taxaMensal = new TaxaMensal();
		for (Cliente cl : banco.getClientes()) {
			for (Conta co : cl.getContas()) {
				taxaMensal.visit(co);
				co.gerarExtrato();
			}
		}
	}

}
