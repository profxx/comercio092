package br.com.senai.comercio092.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.comercio092.entity.Cliente;
import br.com.senai.comercio092.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente insertNew(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente novoCliente){
        Cliente clienteAtual = findById(id);
        clienteAtual.setNome(novoCliente.getNome());
        clienteAtual.setEmail(novoCliente.getEmail());
        clienteAtual.setEndereco(novoCliente.getEndereco());
        clienteAtual.setTelefone(novoCliente.getTelefone());
        return clienteRepository.save(clienteAtual);
    }

    public Boolean deleteById(Long id){
        Cliente cliente = findById(id);
        if (cliente == null){
            return false;
        }else{
            clienteRepository.deleteById(id);
            return true;
        }
    }

}
