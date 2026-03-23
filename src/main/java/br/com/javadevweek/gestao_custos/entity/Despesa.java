package br.com.javadevweek.gestao_custos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String descricao;
    private LocalDate data;
    private BigDecimal valor;
    private String categoria;
    private String email;

    @CreatedDate
    private LocalDate data_criacao;

    @Override
    public String toString() {
        return "Despesa{" +
                "id= " + id +
                ", descricao= '" + descricao + '\'' +
                ", data= " + data +
                ", valor= " + valor +
                ", categoria= '" + categoria + '\'' +
                ", email= '" + email + '\'' +
                ", data_criacao= " + data_criacao +
                '}';
    }
}
