package ar.edu.utn.dds.k3003.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ar.edu.utn.dds.k3003.model.Fuente;
//CLASE CON EL PROPOSITO DE HACER DE REPOSITORIO PARA LAS FUENTES.

public class FuenteRepository {
    private List<Fuente> fuentes;
    private Long seqId;
    public FuenteRepository(){
        fuentes = new ArrayList<>();
    }
    public List<Fuente> allFuentes(){
        return this.fuentes;
    }
    public void setSeqId(Long secuencia){
        this.seqId = secuencia;
    }
    public Fuente save(Fuente fuenteGuardar){
        if (Objects.isNull(fuenteGuardar.getIdFuente())){
            fuenteGuardar.setIdFuente(seqId);
            this.setSeqId(seqId+1);
            fuentes.add(fuenteGuardar);
        }
        return fuenteGuardar;
    }

    public Fuente findById(Long id_fuente){
       Fuente fuenteEncontrada = fuentes.stream()
        .filter(fuente -> fuente.getIdFuente().equals(id_fuente))
        .findFirst()
        .orElseThrow(null);
       return fuenteEncontrada;
    }
}
