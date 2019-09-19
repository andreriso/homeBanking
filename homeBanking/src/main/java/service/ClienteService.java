package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cursoJava.homeBanking.Cliente;
import cursoJava.homeBanking.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
    public ClienteService() {
    }

    public List<Cliente> getAll() {
    	 List<Cliente> clientes = new ArrayList<Cliente>();
        this.repository.findAll().forEach(clientes::add);
        return clientes;
    }

    // Get
    public Optional<Cliente> getById(long id) {
        return this.repository.findById(id);
    }

    // Post
    public void add(Cliente cliente) {
        this.repository.save(cliente);
    }

    // Put
    public void update(Cliente cliente) {
        this.repository.save(cliente);
    }

    // Delete
    public void remove(long id) {
    	Optional<Cliente> cliente = this.getById(id);
        this.repository.delete(cliente.get());
    }

}