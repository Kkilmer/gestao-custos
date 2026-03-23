package br.com.javadevweek.gestao_custos.custom_messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

    private String message;
    private String type;

    public ErrorMessage(String message, String type) {
        this.message = message;
        this.type = type;
    }

}
