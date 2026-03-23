package br.com.javadevweek.gestao_custos.useCase;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatastroDespesaUseCase {
    // SOLID
    // Single Responsability Principle

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa execute(Despesa despesa) {
        despesaRepository.save(despesa);
        return despesa;
    }


}
