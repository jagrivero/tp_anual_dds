package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.facades.FachadaFuente;
import ar.edu.utn.dds.k3003.facades.dtos.PdIDTO;
import ar.edu.utn.dds.k3003.model.Pdi;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PdIDTO> crearPDI(@RequestBody Pdi pdi) {
        PdIDTO pdIDTO = new PdIDTO(pdi.getId(),pdi.getHecho(),pdi.getDescripcion(),pdi.getLugar(),pdi.getMomento(),pdi.getContenido(),pdi.getEtiquetas());
        try {
            return ResponseEntity.ok(fachadaFuente.agregar(pdIDTO));
        } catch (Exception e){
            return new ResponseEntity<>(new PdIDTO("null","null","null","null",LocalDateTime.now(),"null",new ArrayList<>()),HttpStatus.BAD_REQUEST);
        }
    }
} 
