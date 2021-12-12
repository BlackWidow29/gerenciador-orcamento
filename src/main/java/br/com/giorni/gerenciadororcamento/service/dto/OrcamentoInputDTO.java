package br.com.giorni.gerenciadororcamento.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(onConstructor_={@Default})
public class OrcamentoInputDTO {
    Long id;
    @NonNull
    String observacoes;
    @NonNull
    @JsonProperty("id_servicos")
    List<Long> idServicos;
    @JsonProperty("id_cliente")
    long idCliente;
}
