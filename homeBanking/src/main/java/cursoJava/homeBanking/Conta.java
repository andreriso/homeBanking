package cursoJava.homeBanking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cursoJava.homeBanking.Movimentacao.TipoMovimentacao;

@Entity
@DiscriminatorColumn(name = "TIPO_CONTA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Conta implements VisitableElement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
	@OneToMany(mappedBy = "conta")
	private List<Movimentacao> movimentacoes;
	@ManyToOne
	private Cliente cliente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSaldo() {
		double saldo = 0;
		if (movimentacoes != null) {
			for (Movimentacao m : movimentacoes) {
				if (m.getTipoMovimentacao().equals(TipoMovimentacao.CREDITO)) {
					saldo += m.getValor();
				} else {
					saldo -= m.getValor();
				}
			}
		}
		return saldo;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Movimentacao creditar(double valor) {
		return this.gerarMovimento(valor, TipoMovimentacao.CREDITO);
	}

	public Movimentacao debitar(double valor) {
		return this.gerarMovimento(valor, TipoMovimentacao.DEBITO);
	}

	private Movimentacao gerarMovimento(double valor, TipoMovimentacao tipoMovimentacao) {
		Movimentacao m = new Movimentacao();
		m.setConta(this);
		m.setTipoMovimentacao(tipoMovimentacao);
		m.setValor(valor);
		if (this.getMovimentacoes() == null) {
			this.setMovimentacoes(new ArrayList<Movimentacao>());
		}
		this.getMovimentacoes().add(m);
		return m;
	}
	
	public void gerarExtrato() {
		System.out.println("----------------EXTRATO--------------");
		System.out.println("Cliente: " + cliente.getNome() + " " + cliente.getSobrenome());
		System.out.println("Conta: " + id);
		System.out.println("Movimenta��es:");
		if (movimentacoes != null) {
			Stream<Movimentacao> streamMov = movimentacoes.stream();
			streamMov.forEach(m -> System.out.println(m.toString()));
		}
		System.out.println("SALDO: " + getSaldo());
	}
	
	@Override
	public void accept(ContaVisitor visitor) {
		visitor.visit(this);
	}	
	
	public String toString() {
		return  String.valueOf(id);
	}
}
