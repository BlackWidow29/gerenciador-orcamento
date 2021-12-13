package br.com.giorni.gerenciadororcamento.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor_={@Default})
public class EmpresaDTO {
    Long id;
    @JsonProperty("nome_fantasia")
    String nomeFantasia;
    String numero;
    String cep;
    String cidade;
    String bairro;
    String logradouro;
    String estado;
    String telefone;
    String email;
}
