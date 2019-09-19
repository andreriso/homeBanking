package cursoJava.homeBanking;

import java.util.ArrayList;
import java.util.List;

public final class Banco {

	private long codigo;
	private List<Cliente> clientes;

	public Banco(long codigo) {
		this.setCodigo(codigo);
		setClientes(new ArrayList<Cliente>());
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

}
