package br.com.unibratec.unibratecheros.deserializer;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.unibratec.unibratecheros.model.Appearance;
import br.com.unibratec.unibratecheros.model.Biography;
import br.com.unibratec.unibratecheros.model.Hero;
import br.com.unibratec.unibratecheros.model.Powerstats;

public class HeroResponseDeserializer extends JsonDeserializer<HeroResponse> {
    @Override
    public HeroResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Log.d("heee", "teste");
        JsonNode node = jp.readValueAsTree();
        HeroResponse r = new HeroResponse();
        r.resultsFor = node.get("results-for").asText("");
        r.heroes = new ArrayList<>();

        for (JsonNode array : node.get("results")) {
            Hero h = new Hero();
            h.id = array.get("id").asInt();
            h.name = array.get("name").asText();


            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            h.powerstats = mapper.treeToValue(array.get("powerstats"), Powerstats.class);
            h.biography = mapper.treeToValue(array.get("biography"), Biography.class);
            h.appearance = mapper.treeToValue(array.get("appearance"), Appearance.class);
            h.imagem = array.get("image").get("url").asText();
            r.heroes.add(h);
        }

        return r;
    }


}
