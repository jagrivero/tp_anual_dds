package ar.edu.utn.dds.k3003.clients;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import ar.edu.utn.dds.k3003.facades.dtos.PdIDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

@Service
public interface ProcesadorPdIRetrofit {
    @POST("/api/pdis")
    Call<PdIDTO> procesar(@Body PdIDTO pdi);

    @GET("/api/pdis")
    Call<List<PdIDTO>> pdisHechos(@Param("hecho") String hecho);
}

