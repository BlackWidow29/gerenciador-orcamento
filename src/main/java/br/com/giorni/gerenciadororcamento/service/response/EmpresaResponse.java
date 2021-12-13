package br.com.giorni.gerenciadororcamento.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaResponse {
    private Long id;
    @JsonProperty("nome_fantasia")
    private String nomeFantasia;
    private String numero;
    private String cep;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String estado;
    private String telefone;
    private String email;
}
