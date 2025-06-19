package ar.edu.utn.dds.k3003.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Entity;

@Data
@Entity
public class Fuente {
    @Id
    private Long idFuente;
    @Column
    private String nombre;
    @Column
    private String endpoint;
    public Fuente(Long id_,String nombre_,String endpoint_){
        idFuente = id_;
        nombre = nombre_;
        endpoint = endpoint_;
    }
    public Long getIdFuente(){
        return this.idFuente;
    }
    public void setIdFuente(Long idNuevo){
        this.idFuente = idNuevo;
    }
    public void setNombre(String nombreNuevo){
        this.nombre = nombreNuevo;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getEndpoint(){
        return this.endpoint;
    }
    public void setEndpoint(String endpointNuevo){
        this.endpoint = endpointNuevo;
    }
}
