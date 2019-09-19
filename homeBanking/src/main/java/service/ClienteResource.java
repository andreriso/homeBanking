package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cursoJava.homeBanking.Cliente;

@RestController
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Cliente> getAll() {
        List<Cliente> allClientes = clienteService.getAll();
        return allClientes;
    }

    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Cliente get(@RequestParam(name = "id") long id) {
        Optional<Cliente> cliente = clienteService.getById(id);
        return cliente.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Cliente add(Cliente cliente) {
        clienteService.add(cliente);
        return cliente;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Cliente updateCliente (Cliente cliente) {
        clienteService.update(cliente);
        return cliente;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCliente(@RequestParam(name = "id") long id) {
        clienteService.remove(id);
    }
    
}
