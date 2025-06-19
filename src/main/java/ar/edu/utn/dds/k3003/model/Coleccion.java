package ar.edu.utn.dds.k3003.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
public class Coleccion {
    @Id
    private String id;
    @Column
    private String descripcion;
    public Coleccion(String name,String description){
        this.id = name;
        this.descripcion = description;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String nuevo_id){
        this.id = nuevo_id;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public void setDescripcion(String nueva_descripcion){
        this.descripcion = nueva_descripcion;
    }
}
