package ar.edu.utn.dds.k3003.repositories;


import ar.edu.utn.dds.k3003.facades.dtos.HechoFiltroDTO;
import ar.edu.utn.dds.k3003.model.HechoMongo;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.regex.Pattern;
import java.util.List;

@Component
@Repository
@RequiredArgsConstructor
public class HechoMongoSearchRepositoryImpl{
  private final MongoTemplate mongoTemplate;
 
  @Autowired
  private HechoMongoRepository repositoryMongo;
  public void guardarDesdeDTO(HechoMongo hechoMongo) {
    repositoryMongo.save(hechoMongo);
  }
  public HechoMongo buscarPorId(String id){
    return mongoTemplate.findById(id,HechoMongo.class);
  }
  public List<HechoMongo> buscarConFiltros(HechoFiltroDTO filtro) {
    Query query = new Query();
    if (filtro.getTitulo() != null && !filtro.getTitulo().isBlank()) {
      String pattern = "(?i).*" + Pattern.quote(filtro.getTitulo()) + "([^a-zA-Záéíóúñ]|$).*";
      query.addCriteria(Criteria.where("titulo").regex(pattern));
    }

    if (filtro.getCategoria() != null && !filtro.getCategoria().isBlank()) {
      String pattern = "(?i).*" + Pattern.quote(filtro.getCategoria()) + "([^a-zA-Záéíóúñ]|$).*";
      query.addCriteria(Criteria.where("categoria").regex(pattern));  
    }

    if (filtro.getUbicacion() != null && !filtro.getUbicacion().isBlank()) {
        String pattern = "(?i).*" + Pattern.quote(filtro.getUbicacion()) + "([^a-zA-Záéíóúñ]|$).*";
        query.addCriteria(Criteria.where("ubicacion").regex(pattern));
    }

    if (filtro.getEtiquetas() != null && !filtro.getEtiquetas().isBlank()) {
        String pattern = "(?i).*" + Pattern.quote(filtro.getEtiquetas()) + ".*";
        query.addCriteria(Criteria.where("etiquetas").regex(pattern));
    }

    if (filtro.getOrigen() != null && !filtro.getOrigen().isBlank()) {
        String pattern = "(?i).*" + Pattern.quote(filtro.getOrigen()) + "([^a-zA-Záéíóúñ]|$).*";
        query.addCriteria(Criteria.where("origen").regex(pattern));
    }
    if (filtro.getEstado() != null && !filtro.getEstado().isBlank()) {
        String pattern = "(?i).*" + Pattern.quote(filtro.getEstado()) + "([^a-zA-Záéíóúñ]|$).*";
        query.addCriteria(Criteria.where("estado").regex(pattern));
    }
    return mongoTemplate.find(query, HechoMongo.class);
  }
}