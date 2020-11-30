package com.practica.Service;

import com.aplicacion.cliente.domains.ClienteDTO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.practica.Repository.VisitaRepository;
import com.practica.entidades.Visitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    public ClienteDTO buscarCliente(String nombre){
        Application app = eurekaClient.getApplication("discovery-cliente");

        List<InstanceInfo> lista = app.getInstances();

        RestTemplate restTemplate = new RestTemplate();

        String fooResourceUrl = lista.get(0).getHomePageUrl();

        return restTemplate.getForEntity(fooResourceUrl + "cliente/buscar/" + nombre, ClienteDTO.class).getBody();

    }

    public Visitas guardar(Visitas visitas){
        return visitaRepository.save(visitas);
    }

    public List<Visitas> buscarEstado(String estado){
        return visitaRepository.findByEstado(estado);
    }

    public Visitas buscarVisitaCliente(String cliente){
        return visitaRepository.findByCliente_Nombre(cliente);
    }

}