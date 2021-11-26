package br.com.giorni.gerenciadororcamento.controller;

import br.com.giorni.gerenciadororcamento.model.MaterialServico;
import br.com.giorni.gerenciadororcamento.model.Servico;
import br.com.giorni.gerenciadororcamento.service.ServicoService;
import br.com.giorni.gerenciadororcamento.service.dto.MaterialServicoDTO;
import br.com.giorni.gerenciadororcamento.service.dto.ServicoDTO;
import br.com.giorni.gerenciadororcamento.service.response.ServicoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public MaterialServico createServiceWithMaterial(@RequestBody MaterialServicoDTO materialServicoDTO){
        return servicoService.save(materialServicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponse>> findAll(){
        return ResponseEntity.ok().body(servicoService.findAll());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> findById(@PathVariable Long id){
        return servicoService.findById(id)
                .map(servicoDTO -> ResponseEntity.ok().body(servicoDTO))
                .orElse(ResponseEntity.notFound().build());
    }

//    @PutMapping
//    public ResponseEntity<?> update(@RequestBody ServicoDTO servicoDTO){
//        return ResponseEntity.ok(servicoService.update(servicoDTO));
//    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id){
        boolean deletou = servicoService.delete(id);
        return deletou ? ResponseEntity.ok().body("Serviço removido com sucesso") : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/calcularValorTotalServico/{id}")
    public Double calcularValorTotalServico(@PathVariable Long id){
        return servicoService.calcularValorTotalServico(id);
    }
}