package br.com.javadevweek.gestao_custos.service;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatastroDespesaService {
    // SOLID
    // Single Responsability Principle

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa execute(Despesa despesa) {
        if (despesa.getEmail() == null || despesa.getCategoria() == null ||
        despesa.getDescricao() == null || despesa.getData() == null) {
            throw new IllegalArgumentException("Preencher todos os campos");
        }
        despesaRepository.save(despesa);
        return despesa;
    }


    public void findByEmailAndDate() {

    }

}
