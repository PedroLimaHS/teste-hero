package br.com.unibratec.unibratecheros.internet;

import java.util.List;

import br.com.unibratec.unibratecheros.deserializer.HeroResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HeroRest {

    // Interface para o endpoit de repositórios de um usuário específico
    @GET("search/{name}")
    Call<HeroResponse> searchHero(@Path("name") String name);
}