package br.com.toolschallenge.util;

import br.com.toolschallenge.model.Transacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe para abstrair as convercoes de objetos
 *
 * @param <T> Representa a classe Dto
 * @param <U> Representa a classe Model
 */
public class Converter<T, U> {

    private final Function<T, U> fromDto;
    private final Function<U, T> fromModel;

    public Converter(final Function<T, U> fromDto, final Function<U, T> fromModel) {
        this.fromDto = fromDto;
        this.fromModel = fromModel;
    }

    public final U converterDto(final T dto) {
        return fromDto.apply(dto);
    }

    public final T converterModel(final U model) {
        return fromModel.apply(model);
    }

    public final List<U> converterDtolList(final Collection<T> dtoList) {
        if (dtoList.isEmpty()) return new ArrayList<>();
        return dtoList.stream().map(this::converterDto).collect(Collectors.toList());
    }

    public final List<T> converterModelList(final Collection<U> modelList) {
        if (modelList.isEmpty()) return new ArrayList<>();
        return modelList.stream().map(this::converterModel).collect(Collectors.toList());
    }
}
