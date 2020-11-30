package com.practica.pagos.Controller;

import com.practica.entidades2.Entidades.Facturas;
import com.practica.entidades2.Entidades.Pagos;
import com.practica.pagos.Service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagosController {

    @Autowired
    PagosService pagosService;

    @PostMapping("/guardar")
    public ResponseEntity<?> buscarEstado(@RequestBody Pagos pago){
        Pagos pagoC = pagosService.guardar(pago);

        if(pagoC != null){
            return new ResponseEntity<>(pagoC, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarcliente/{nombre}")
    public ResponseEntity<?> guardarFactura(@PathVariable("nombre") String nombre){
        List<Pagos> pagos = pagosService.buscarCliente(nombre);

        if(pagos != null){
            return new ResponseEntity<>(pagos, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

    }
}
