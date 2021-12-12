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
public class ClienteSemOrcamentoResponse {
    private Long id;
    @JsonProperty("tipo_cliente")
    private String tipoCliente;
    private String email;
    private String nome;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    private String telefone;
    private String numero;
    private String cep;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String estado;
}
