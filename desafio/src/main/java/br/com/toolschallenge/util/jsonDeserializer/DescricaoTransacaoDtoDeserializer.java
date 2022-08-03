package br.com.toolschallenge.util.jsonDeserializer;

import br.com.toolschallenge.dto.DescricaoTransacaoDto;
import br.com.toolschallenge.enums.TipoStatusTransacao;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DescricaoTransacaoDtoDeserializer implements JsonDeserializer<DescricaoTransacaoDto> {

    @Override
    public DescricaoTransacaoDto deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jObject = jsonElement.getAsJsonObject();
        final BigDecimal valor = jObject.get("valor").getAsBigDecimal();
        final LocalDateTime dataHora = LocalDateTime.parse(jObject.get("dataHora").getAsString());
        final String estabelecimento = jObject.get("estabelecimento").getAsString();
        final String nsu = jObject.get("nsu").getAsString();
        final String codigoAutorizacao = jObject.get("codigoAutorizacao").getAsString();
        final TipoStatusTransacao status = TipoStatusTransacao.valueOf(jObject.get("status").getAsString());

        return new DescricaoTransacaoDto(valor, dataHora, estabelecimento, nsu, codigoAutorizacao, status);
    }

}
