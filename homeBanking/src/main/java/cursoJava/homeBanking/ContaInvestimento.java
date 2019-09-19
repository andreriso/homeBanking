package cursoJava.homeBanking;

import java.util.function.Consumer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("I")
public class ContaInvestimento extends Conta {

	private TipoAplicacao tipoAplicacao;

	public TipoAplicacao getTipoAplicacao() {
		return tipoAplicacao;
	}

	public void setTipoAplicacao(TipoAplicacao tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}
	
	public ContaInvestimento (Builder builder) {
		this.tipoAplicacao = builder.tipoAplicacao;
		this.setId(builder.id);
		this.setCliente(builder.cliente);
	}
	
	
	public static class Builder {
		public int id;
		public Cliente cliente;
		public TipoAplicacao tipoAplicacao;

		public Builder(int id, Cliente cliente, TipoAplicacao tipoAplicacao) {
			this.id = id;
			this.cliente = cliente;
			this.tipoAplicacao = tipoAplicacao;
		}

		public Builder set(Consumer<Builder> builderConsumer) {
			builderConsumer.accept(this);
			return this;
		}

		public ContaInvestimento build() {
			ContaInvestimento ci = new ContaInvestimento(this);
			if (ci.getCliente() != null) {
				ci.getCliente().getContas().add(ci);
			}
			return ci;
		}
	}	
}
