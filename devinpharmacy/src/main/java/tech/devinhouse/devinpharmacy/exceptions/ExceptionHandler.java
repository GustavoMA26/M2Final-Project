package tech.devinhouse.devinpharmacy.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.devinhouse.devinpharmacy.dto.ErroResponse;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handleCnpjNaoEncontradoException(CnpjNaoEncontradoException ex) {
        ErroResponse error = new ErroResponse("CNPJ NÃO ENCONTRADO", "Não encontramos nenhuma farmácia com este CNPJ. Tente novamente!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handleCnpjExistenteException(CnpjExistenteException ex) {
        ErroResponse erro = new ErroResponse("CNPJ JÁ CADASTRADO", "Este CNPJ já se encontra cadastrado em outra farmácia");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });
        ErroResponse error = new ErroResponse("Erro de Validação", "Campos Obrigatórios",
                fieldErrors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
