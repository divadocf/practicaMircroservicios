package com.practica.Controller;

import com.aplicacion.cliente.domains.VisitasDTO;
import com.practica.Service.VisitaService;
import com.practica.entidades.Visitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitas")
public class VisitasController {

    @Autowired
    private VisitaService visitaService;

    @GetMapping("/buscarestado/{estado}")
    public ResponseEntity<?> buscarEstado(@PathVariable("estado") String estado){
        List<Visitas> visitas = visitaService.buscarEstado(estado);

        if(visitas != null){
            return new ResponseEntity<>(visitas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarcliente/{nombre}")
    public ResponseEntity<?> buscarCliente(@PathVariable("nombre") String nombre){
        Visitas visitas = visitaService.buscarVisitaCliente(nombre);

        VisitasDTO visitaB = new VisitasDTO();

        visitaB.setEstado(visitas.getEstado());
        visitaB.setFecha(visitas.getFecha());
        visitaB.setId(visitas.getId());
        visitaB.setImporte(visitas.getImporte());
        //visitaB.setCliente(visitaService.buscarCliente(visitas.getCliente().getNombre()));

        if(visitaB != null){
            return new ResponseEntity<>(visitaB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/buscarclientevisita")
    public ResponseEntity<?> buscarClienteVisita(@RequestBody Visitas visita){
        Visitas visitas = visitaService.guardar(visita);

        if(visitas != null){
            return new ResponseEntity<>(visitas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}