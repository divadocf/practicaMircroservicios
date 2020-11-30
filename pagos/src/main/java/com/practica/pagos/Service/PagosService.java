package com.practica.pagos.Service;

import com.aplicacion.cliente.domains.ClienteDTO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.practica.entidades2.Entidades.Facturas;
import com.practica.entidades2.Entidades.Pagos;
import com.practica.pagos.Repository.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PagosService {

    @Autowired
    PagosRepository pagoRepository;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    public String recogerFactura(String id){

        Application app = eurekaClient.getApplication("facturas");

        List<InstanceInfo> lista = app.getInstances();
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = lista.get(0).getHomePageUrl();

        return restTemplate.getForEntity(fooResourceUrl + "/facturas/buscarId/" + id, Facturas.class).getBody().getId();

    }

    public Pagos guardar(Pagos pago){
        pago.setFacturaid(recogerFactura(pago.getFacturaid()));

        return pagoRepository.insert(pago);
    }

    public String buscarCliente(String nombre){
        Application app = eurekaClient.getApplication("clientes");

        List<InstanceInfo> lista = app.getInstances();
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = lista.get(0).getHomePageUrl();

        return restTemplate.getForEntity(fooResourceUrl + "cliente/buscar/" + nombre, ClienteDTO.class).getBody().getId();

    }

    public List<Pagos> buscarCliente(String nombre){
        return pagoRepository.findByClienteid(buscarCliente(nombre));
    }
}
