package br.com.giorni.gerenciadororcamento.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor(onConstructor_={@Default})
public class MaterialServicoDTO {
    Long id;
    @JsonProperty("quantidade_material")
    Integer quantidadeMaterial;
    MaterialDTO material;
    ServicoDTO servico;
}
