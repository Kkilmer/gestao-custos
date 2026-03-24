package br.com.javadevweek.gestao_custos.performance;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Component
public class GestaoDeDespesaSeeder implements CommandLineRunner {

    @Autowired
    DespesaRepository despesaRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Despesa> despesas = new ArrayList<>();
        System.out.println("Iniciando geração de seed");

        for (int i=0; i <= 150000; i++){
            Despesa despesa = new Despesa();
            despesa.setDescricao("Gasto nº " + i); // criar uma descrição, ex.: Gasto nº1, Gasto nº2...
            despesa.setValor(BigDecimal.valueOf(10 + (i % 50))); // variação entre 1 e 50
            despesa.setData(LocalDate.now().minusDays(i % 30)); // máximo de 1 - 30 dias
            despesa.setCategoria("TESTE");
            despesa.setEmail("performance@gmail.com");

            despesas.add(despesa);
        }

        despesaRepository.saveAll(despesas);
        System.out.println("Finalizou geração de seed");
    }
}
