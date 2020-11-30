package com.practica.Service;


import com.practica.Repository.ClienteRepository;
import com.practica.entidades.Cliente;
import com.practica.entidades.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente guardar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente listarCliente(int id){
        return clienteRepository.findById(id);
    }

    public Cliente buscarNombre(String nombre){
        return clienteRepository.findByNombre(nombre);
    }

    public List<Cliente> buscarDireccion(String provincia){
        return clienteRepository.findByDireccion_Provincia(provincia);
    }

    public void eliminar(Cliente cliente){
        clienteRepository.delete(cliente);
    }

}
