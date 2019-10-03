package cursoJava.homeBanking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	
	enum TipoMovimentacao {
		CREDITO, DEBITO
	}

	private TipoMovimentacao tipoMovimentacao;
	private double valor;
	@ManyToOne
	private Conta conta;
	
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public String toString() {
		return "Tipo Movimentacao :" + tipoMovimentacao.toString() + " Valor :" + String.valueOf(valor);
	}
	
	public String getInfo() {
		return "Banco=" + this.getConta().getCliente().getBanco().getCodigo() + " Cliente=" + this.getConta().getCliente().toString() 
				+ " Conta=" + this.getConta().toString() + " Movimentação=" + this.getTipoMovimentacao().toString() + " " + this.getValor();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
