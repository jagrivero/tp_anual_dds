package ar.edu.utn.dds.k3003.clients;
import ar.edu.utn.dds.k3003.facades.FachadaProcesadorPdI;
import ar.edu.utn.dds.k3003.facades.FachadaSolicitudes;
import ar.edu.utn.dds.k3003.facades.dtos.PdIDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.HttpStatus;
import java.util.*;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ProcesadorPdiProxy implements FachadaProcesadorPdI{
  private final String endpoint;
  private final ProcesadorPdIRetrofit service;

  public ProcesadorPdiProxy(ObjectMapper objectMapper) {
    var env = System.getenv();
    this.endpoint = env.getOrDefault("URL_PROCESADOR", "http://localhost:8080/");
    if(this.endpoint.equals("http://localhost:8080/")){
      System.out.println("FAILURE");
    } else {
      System.out.println("APPARENT SUCCESS");
    }
    System.out.println(this.endpoint);
    var retrofit =
        new Retrofit.Builder()
            .baseUrl(this.endpoint)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build();
    this.service = retrofit.create(ProcesadorPdIRetrofit.class);
  }
  @SneakyThrows
  @Override
  public PdIDTO procesar(PdIDTO pdi_dto) throws NoSuchElementException {
    // TODO ????
    System.out.println("Llegamos opweop");
    Call<List<PdIDTO>> execute1 = service.pdisHechos("E");
    execute1.enqueue(new Callback<List<PdIDTO>>(){
      @Override
      public void onResponse(Call<List<PdIDTO>> execute1, Response<List<PdIDTO>> respuesta){
        if(!respuesta.isSuccessful()){
          System.out.println("Errores de codigo: "+ respuesta.code());
          return;
        } 
        List<PdIDTO> vamos_de_nuevo = respuesta.body();
        System.out.println(vamos_de_nuevo);
      }
      @Override
      public void onFailure(Call<List<PdIDTO>> execute1, Throwable t){
        System.out.println("Errores de failure: "+ t.getMessage());
        return;
      }
    });
    try{
      String veamos_adentro = pdi_dto.toString();
      System.out.println("Tercer paso" + veamos_adentro);
      Response<PdIDTO> execute = service.procesar(pdi_dto).execute();
      if (execute.isSuccessful()) {
        return execute.body();
      }
      System.out.println("El codigo de salida fue: " + execute.code());
      if (execute.code() == HttpStatus.NOT_FOUND.getCode()) {
        System.out.println("Hubo un error ejecutando");
        throw new NoSuchElementException("No se pudo procesar la pieza de informacion");
      }
    } catch (Exception e) {
      System.out.println("Los caminos de la vida no son como yo esperaba");
    }
    //get(pdi_dto).execute(); //TODO ????
    
    System.out.println("    lecnn oid qdho  ");
    throw new RuntimeException("Error conectandose con el componente procesador");
  }
  @Override
  public PdIDTO buscarPdIPorId(String pdiId) throws NoSuchElementException {
    throw new UnsupportedOperationException("Unimplemented method 'buscarPdIPorId'");
  }

  @Override
  public List<PdIDTO> buscarPorHecho(String hechoId) throws NoSuchElementException {
    throw new UnsupportedOperationException("Unimplemented method 'buscarPorHecho'");
  }

  @Override
  public void setFachadaSolicitudes(FachadaSolicitudes fachadaSolicitudes) {
    throw new UnsupportedOperationException("Unimplemented method 'setFachadaSolicitudes'");
  }
}
