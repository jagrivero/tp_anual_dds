package ar.edu.utn.dds.k3003.facades.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record HechoDTO(String id,String nombreColeccion, String titulo, List<String> etiquetas,
                       CategoriaHechoEnum categoria,
                       String ubicacion, LocalDateTime fecha, String origen) {

  public HechoDTO(String id,String nombreColeccion, String titulo) {
    this(id, nombreColeccion, titulo, null, null, null, null, null);
  }
}
