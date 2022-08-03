package br.com.toolschallenge.controller.erro;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidacaoErrorHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public String handle(MethodArgumentNotValidException expection) {
        return new Gson().toJson(
                expection.getBindingResult().getFieldErrors().stream()
                        .map(field -> field.getField() + " : " + field.getDefaultMessage())
                        .collect(Collectors.toList())
        );
    }

}
