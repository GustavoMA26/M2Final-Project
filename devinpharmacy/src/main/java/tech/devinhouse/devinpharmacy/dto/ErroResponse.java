package tech.devinhouse.devinpharmacy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ErroResponse {

    private String titulo;

    private String mensagem;

    private Map<String, String> detalhes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;


    public ErroResponse(String title, String message, Map details) {
        this.titulo = title;
        this.mensagem = message;
        this.detalhes = details;
        this.timestamp = LocalDateTime.now();
    }

    public ErroResponse(String title, String message) {
        this(title, message, null);
    }

    public ErroResponse(String title) {
        this(title, null);
    }

}
