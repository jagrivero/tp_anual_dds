package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.facades.FachadaFuente;
import ar.edu.utn.dds.k3003.facades.dtos.PdIDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pdi")
public class PdiController {

    private final FachadaFuente fachadaFuente;

    @Autowired
    public PdiController(FachadaFuente fachadaFuente) {
        this.fachadaFuente = fachadaFuente;
    }

    @PostMapping
    public ResponseEntity<PdIDTO> crearPDI(@RequestBody PdIDTO pdIDTO) {
        return ResponseEntity.ok(fachadaFuente.agregar(pdIDTO));
    }
} 