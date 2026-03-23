package br.com.javadevweek.gestao_custos.controller;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.useCase.CatastroDespesaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/gestao")
@RestController
public class GestaoDespesaController {

    /*
        * - Cadastro de despesa
        * - Criar tabela no banco de dados
        * - Criar entidade
     */

    @Autowired
    CatastroDespesaUseCase catastroDespesaUseCase;

    @PostMapping("/create")
    public Despesa create(@RequestBody Despesa despesa) {
        return catastroDespesaUseCase.execute(despesa);
    }
}
