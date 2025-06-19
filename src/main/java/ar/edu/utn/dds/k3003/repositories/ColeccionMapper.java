package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.facades.dtos.ColeccionDTO;
import ar.edu.utn.dds.k3003.model.Coleccion;

public class ColeccionMapper{
    public ColeccionDTO map(Coleccion coleccion){
        if(coleccion == null){
            return null;
        }
        ColeccionDTO retorno = new ColeccionDTO(coleccion.getId(),coleccion.getDescripcion());
        return retorno;
    }
    public Coleccion map(ColeccionDTO colecciondto){
        if(colecciondto == null){
            return null;
        }
        Coleccion coleccionMappeada = new Coleccion(colecciondto.nombre(),colecciondto.descripcion());
        return coleccionMappeada;
    }
}
