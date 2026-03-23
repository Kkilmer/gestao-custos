package br.com.javadevweek.gestao_custos.controller;

import br.com.javadevweek.gestao_custos.custom_messages.ErrorMessage;
import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.service.BuscarDespesaService;
import br.com.javadevweek.gestao_custos.service.CatastroDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/gestao")
@RestController
public class GestaoDespesaController {

    /*
        * - Cadastro de despesa
        * - Criar tabela no banco de dados
        * - Criar entidade
     */

    @Autowired
    CatastroDespesaService catastroDespesaService;

    @Autowired
    BuscarDespesaService buscarDespesaService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Despesa despesa) {
        try {
            var result= catastroDespesaService.execute(despesa);
            return ResponseEntity.ok(result);

        }catch (IllegalArgumentException e){
            var errorMessage = new ErrorMessage(e.getMessage(),"INVALID_PARAMS");
            return ResponseEntity.status(400).body(errorMessage);
        }
    }

    // /gestao/find/kp827767@gmail.com?data=2026-03-23
    @GetMapping("/{email}")
    public List<Despesa> findByEmailAndDate(@PathVariable String email,
                                            @RequestParam(required = false)LocalDate data) {
        return buscarDespesaService.execute(email, data);
    }
}
