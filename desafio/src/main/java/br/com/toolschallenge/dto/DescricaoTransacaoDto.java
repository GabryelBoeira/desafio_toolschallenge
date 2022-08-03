package br.com.toolschallenge.dto;

import br.com.toolschallenge.enums.TipoStatusTransacao;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DescricaoTransacaoDto(
        @Positive BigDecimal valor,
        @NotNull
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        LocalDateTime dataHora,
        @NotBlank String estabelecimento,
        String nsu,
        String codigoAutorizacao,
        TipoStatusTransacao status) {
}
