package com.practica.Repository;

import com.practica.entidades.Visitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.List;

public interface VisitaRepository extends Repository<Visitas, Integer> {

    Visitas save(Visitas visitas);

    Visitas findById(int id);

    Visitas findByCliente_Nombre(String cliente);

    List<Visitas> findByEstado(String estado);

}