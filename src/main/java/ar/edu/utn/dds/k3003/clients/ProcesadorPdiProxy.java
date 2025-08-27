package ar.edu.utn.dds.k3003.clients;
import ar.edu.utn.dds.k3003.facades.FachadaProcesadorPdI;
import ar.edu.utn.dds.k3003.facades.FachadaSolicitudes;
import ar.edu.utn.dds.k3003.facades.dtos.PdIDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.HttpStatus;
import java.util.*;
import lombok.SneakyThrows;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ProcesadorPdiProxy implements FachadaProcesadorPdI{
  private final String endpoint;
  private final ProcesadorPdIRetrofit service;

  public ProcesadorPdiProxy(ObjectMapper objectMapper) {
    var env = System.getenv();
    this.endpoint = env.getOrDefault("URL_PROCESADOR", "http://localhost:8081/");
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
    Response<PdIDTO> execute = service.procesar(pdi_dto).execute();
    //get(pdi_dto).execute(); //TODO ????
    if (execute.isSuccessful()) {
      return execute.body();
    }
    if (execute.code() == HttpStatus.NOT_FOUND.getCode()) {
      throw new NoSuchElementException("No se pudo procesar la pieza de informacion");
    }
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
