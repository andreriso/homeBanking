package cursoJava.homeBanking;

import java.util.function.Consumer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class ContaCorrente extends Conta {

	private double limiteCredito;

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public ContaCorrente(Builder builder) {
		this.limiteCredito = builder.limiteCredito;
		this.setId(builder.id);
		this.setCliente(builder.cliente);
	}

	public static class Builder {
		public int id;
		public Cliente cliente;
		public double limiteCredito;

		public Builder(int id, Cliente cliente, double limiteCredito) {
			this.id = id;
			this.cliente = cliente;
			this.limiteCredito = limiteCredito;
		}

		public Builder(int id, double limiteCredito) {
			this.id = id;
			this.limiteCredito = limiteCredito;
		}

		public Builder set(Consumer<Builder> builderConsumer) {
			builderConsumer.accept(this);
			return this;
		}

		public ContaCorrente build() {
			ContaCorrente cc = new ContaCorrente(this);
			if (cc.getCliente() != null) {
				cc.getCliente().getContas().add(cc);
			}
			return cc;
		}
	}
}
