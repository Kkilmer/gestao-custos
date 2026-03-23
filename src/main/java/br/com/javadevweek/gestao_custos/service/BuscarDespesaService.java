package br.com.javadevweek.gestao_custos.service;

import br.com.javadevweek.gestao_custos.entity.Despesa;

import br.com.javadevweek.gestao_custos.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BuscarDespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<Despesa> execute(String email, LocalDate data){

        List<Despesa> despesas;
        if (data != null) {
            despesas = despesaRepository.findByEmailAndData(email, data);
        }else {
            despesas = despesaRepository.findByEmail(email);
        }
        return despesas;
    }
}
