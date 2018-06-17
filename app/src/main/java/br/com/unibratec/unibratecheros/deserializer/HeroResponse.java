package br.com.unibratec.unibratecheros.deserializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Collection;
import java.util.List;

import br.com.unibratec.unibratecheros.model.Hero;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = HeroResponseDeserializer.class)
public class HeroResponse {
    public String reponse;
    @JsonProperty("results-for")
    public String resultsFor;
    @JsonProperty("results")
    public List<Hero> heroes;
}
