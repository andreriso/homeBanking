package cursoJava.homeBanking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private String sobrenome;
	private String endereco;
	private String telefone;
	@OneToMany(mappedBy = "cliente")
	private List<Conta> contas;
	@ManyToOne
	private Banco banco;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public String toString() {
		return this.nome + " " + this.sobrenome;
	}
	
	public Cliente(Builder builder) {
		this.nome = builder.nome;
		this.sobrenome = builder.sobrenome;
		this.endereco = builder.endereco;
		this.telefone = builder.telefone;
		this.contas = builder.contas;
		this.banco = builder.banco;
		for(Conta c : contas) {
			c.setCliente(this);
		}
	}

	public static class Builder {
		public String nome;
		public String sobrenome;
		public String endereco;
		public String telefone;
		public List<Conta> contas = new ArrayList<Conta>();
		private Banco banco;

		public Builder(Banco banco, String nome, String sobrenome) {
			this.banco = banco;
			this.nome = nome;
			this.sobrenome = sobrenome;
		}

		public Builder set(Consumer<Builder> builderConsumer) {
			builderConsumer.accept(this);
			return this;
		}

		public Builder comTelefone(String telefone) {
			this.telefone = telefone;			
			return this;
		}
		
		public Builder comEndereco(String endereco) {
			this.endereco = endereco;			
			return this;
		}		
		
		public Builder comContas(Conta ...contas) {
			for(Conta c : contas) {
				if(!this.contas.contains(c)) {
					this.contas.add(c);
				}
			}					
			return this;
		}				
		
		public Cliente build() {
			Cliente c = new Cliente(this);
			banco.getClientes().add(c);
			return c;
		}					
	}
}
