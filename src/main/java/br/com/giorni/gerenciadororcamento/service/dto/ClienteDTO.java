package br.com.giorni.gerenciadororcamento.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(onConstructor_={@Default})
public class ClienteDTO {
    Long id;
    @JsonProperty("tipo_cliente")
    String tipoCliente;
    String email;
    String nome;
    List<OrcamentoDTO> orcamentos;
    @JsonProperty("cpf_cnpj")
    String cpfCnpj;
    String telefone;
    String numero;
    String cep;
    String cidade;
    String bairro;
    String logradouro;
    String estado;

}
