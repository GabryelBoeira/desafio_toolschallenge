package br.com.toolschallenge.util.jsonDeserializer;

import br.com.toolschallenge.dto.DescricaoTransacaoDto;
import br.com.toolschallenge.dto.FormaPagamentoDto;
import br.com.toolschallenge.enums.TipoFormaPagamento;
import br.com.toolschallenge.enums.TipoStatusTransacao;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FormaPagamentoDtoDeserializer implements JsonDeserializer<FormaPagamentoDto> {

    @Override
    public FormaPagamentoDto deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jObject = jsonElement.getAsJsonObject();
        final TipoFormaPagamento tipo = TipoFormaPagamento.valueOf(jObject.get("tipo").getAsString());
        final Long parcelas = jObject.get("parcelas").getAsLong();

        return new FormaPagamentoDto(tipo, parcelas);
    }

}
