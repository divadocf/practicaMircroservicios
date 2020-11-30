package com.practica.Repository;

import com.practica.entidades2.Entidades.Facturas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends MongoRepository<Facturas, String> {

    Facturas insert(Facturas factura);

    Facturas save(Facturas factura);

    Optional<Facturas> findById(String id);

    List<Facturas> findByEstado(String estado);

    List<Facturas> findByClienteid(int clienteid);

    List<Facturas> findByCuantia(double cuantia);

}
