package br.com.toolschallenge.util.jsonDeserializer;

import br.com.toolschallenge.dto.DescricaoTransacaoDto;
import br.com.toolschallenge.dto.FormaPagamentoDto;
import br.com.toolschallenge.dto.TransacaoDto;
import br.com.toolschallenge.model.DescricaoTransacao;
import br.com.toolschallenge.util.gsonAdapter.LocalDateTimeAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class TransacaoDtoDeserializer implements JsonDeserializer<TransacaoDto> {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DescricaoTransacaoDto.class, new DescricaoTransacaoDtoDeserializer())
            .registerTypeAdapter(FormaPagamentoDto.class, new FormaPagamentoDtoDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe()).create();

    @Override
    public TransacaoDto deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            final JsonObject jObject = jsonElement.getAsJsonObject();
            final String id = jObject.get("id").getAsString();
            final String cartao = jObject.get("cartao").getAsString();
            final DescricaoTransacaoDto descricaoDto = gson.fromJson(jObject.get("descricao").toString(), DescricaoTransacaoDto.class);
            final FormaPagamentoDto formaPagamentoDto =  gson.fromJson(jObject.get("formaPagamento").toString(), FormaPagamentoDto.class);

            return new TransacaoDto(id, cartao, descricaoDto, formaPagamentoDto);
    }
}
