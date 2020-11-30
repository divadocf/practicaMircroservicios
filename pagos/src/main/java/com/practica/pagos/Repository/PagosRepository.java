package com.practica.pagos.Repository;

import com.practica.entidades2.Entidades.Facturas;
import com.practica.entidades2.Entidades.Pagos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PagosRepository extends MongoRepository<Pagos, String> {

    Pagos insert(Pagos pagos);

    Pagos save(Pagos pagos);

    Optional<Pagos> findById(String id);

    List<Pagos> findByEstado(String estado);

    List<Pagos> findByEstadocli(String estado);

    List<Pagos> findByEstadofac(String estado);

    List<Pagos> findByClienteid(int clienteid);
}
