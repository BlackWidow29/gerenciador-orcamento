package br.com.giorni.gerenciadororcamento.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialServicoSemServicoResponse {
    @JsonProperty("quantidade_material")
    private Integer quantidadeMaterialNoServico;
    @JsonProperty("material")
    private MaterialSemFornecedorResponse informacoesSobreOMaterial;
}
