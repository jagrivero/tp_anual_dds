package ar.edu.utn.dds.k3003.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
public class Coleccion {
    @Id
    private String nombre;
    @Column
    private String descripcion;
    public Coleccion(String name,String description){
        this.nombre = name;
        this.descripcion = description;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nuevo_id){
        this.nombre = nuevo_id;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public void setDescripcion(String nueva_descripcion){
        this.descripcion = nueva_descripcion;
    }
}
