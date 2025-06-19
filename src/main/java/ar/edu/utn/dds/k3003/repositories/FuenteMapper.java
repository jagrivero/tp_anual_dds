package ar.edu.utn.dds.k3003.repositories;

import java.util.Objects;

import ar.edu.utn.dds.k3003.facades.dtos.FuenteDTO;
import ar.edu.utn.dds.k3003.model.Fuente;

//CLASE CUYA FUNCIONALIDAD ES LA DE TRANSFORMAR LOS DTOS a clases JAVA 
//o viceversa para facilitar la comunicacion

public class FuenteMapper{
    public FuenteDTO map(Fuente fuente){
        if(fuente.equals(null)){
            return null;
        }
        FuenteDTO retorno = new FuenteDTO(Long.toString(fuente.getIdFuente()),fuente.getNombre(),fuente.getEndpoint());
        return retorno;
    }
    public Fuente  map(FuenteDTO fuenteDTO){
        if(fuenteDTO.equals(null)){
            return null;
        }
        Fuente fuenteMapeada;
        if (Objects.isNull(fuenteDTO.id())){
            fuenteMapeada = new Fuente(null,fuenteDTO.nombre(),fuenteDTO.endpoint());
        } else {
            fuenteMapeada = new Fuente(Long.parseLong(fuenteDTO.id()),fuenteDTO.nombre(),fuenteDTO.endpoint());
        }
        return fuenteMapeada;
    }
}