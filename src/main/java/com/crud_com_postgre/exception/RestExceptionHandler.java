package com.crud_com_postgre.exception;

import com.crud_com_postgre.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {

        // Define o status HTTP
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Instancia nosso objeto de erro customizado
        ValidationError err = new ValidationError(
                Instant.now(),                          // Timestamp atual
                status.value(),                         // Status code (ex: 400)
                "Erro de Validação",                    // Mensagem de erro principal
                "Um ou mais campos estão inválidos.",   // Mensagem detalhada
                request.getRequestURI()                 // Path da requisição (ex: /alunos)
        );

        // Percorre a lista de erros da exceção original e adiciona ao nosso objeto
        for (FieldError f : ex.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        // Retorna o objeto de erro customizado com o status correto
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> handleNoSuchElementException(NoSuchElementException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiResponse response = ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status("RECURSO_NAO_ENCONTRADO")
                .message(ex.getMessage())
                .method(request.getMethod())
                .data(null)
                .build();
        return ResponseEntity.status(status).body(response);
    }
}
