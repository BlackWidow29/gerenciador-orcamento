package br.com.giorni.gerenciadororcamento.service.mapper;

import br.com.giorni.gerenciadororcamento.model.Cliente;
import br.com.giorni.gerenciadororcamento.model.MaterialServico;
import br.com.giorni.gerenciadororcamento.model.Orcamento;
import br.com.giorni.gerenciadororcamento.model.Servico;
import br.com.giorni.gerenciadororcamento.repository.ClienteRepository;
import br.com.giorni.gerenciadororcamento.repository.OrcamentoRepository;
import br.com.giorni.gerenciadororcamento.repository.ServicoRepository;
import br.com.giorni.gerenciadororcamento.service.dto.OrcamentoDTO;
import br.com.giorni.gerenciadororcamento.service.dto.OrcamentoInputDTO;
import br.com.giorni.gerenciadororcamento.service.dto.ServicoDTO;
import br.com.giorni.gerenciadororcamento.service.response.OrcamentoResponse;
import br.com.giorni.gerenciadororcamento.service.response.ServicoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrcamentoMapper {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public static Orcamento toEntity(OrcamentoDTO orcamentoDTO) {
       List<Servico> servicos = new ArrayList<>();
       if (orcamentoDTO.getServicos() != null){
           servicos = ServicoMapper.listServicoDtoToListServico(orcamentoDTO.getServicos());
       }
        return Orcamento
                .builder()
                .id(orcamentoDTO.getId())
                .observacoes(orcamentoDTO.getObservacoes())
                .servicos(servicos)
                .cliente(ClienteMapper.toEntity(orcamentoDTO.getCliente()))
                .valorTotal(orcamentoDTO.getValorTotal())
                .build();
    }

    public Orcamento toEntity(OrcamentoInputDTO orcamentoDTO) {
        List<Servico> servicos = orcamentoDTO.getIdservicos().stream().map(id -> servicoRepository.findById(id).orElseThrow(IllegalArgumentException::new)).collect(Collectors.toList());
        return Orcamento
                .builder()
                .id(orcamentoDTO.getId())
                .observacoes(orcamentoDTO.getObservacoes())
                .servicos(servicos)
                .cliente(clienteRepository.findById(orcamentoDTO.getIdCliente()).orElseThrow(IllegalArgumentException::new))
                .build();
    }

    public static OrcamentoDTO toDto(Orcamento orcamento) {

        return OrcamentoDTO
                .builder()
                .id(orcamento.getId())
                .cliente(ClienteMapper.toDto(orcamento.getCliente()))
                .build();
    }

    public static List<Orcamento> listOrcamentoDtoToListOrcamento(List<OrcamentoDTO> orcamentoDTOList) {
        List<Orcamento> orcamentoList = new ArrayList<>();
        if (orcamentoDTOList.size() > 0) orcamentoDTOList.forEach(orcamentoDTO -> orcamentoList.add(OrcamentoMapper.toEntity(orcamentoDTO)));
        return orcamentoList;
    }

    public static List<OrcamentoDTO> listOrcamentoToListOrcamentoDto(List<Orcamento> orcamentoList) {
        List<OrcamentoDTO> orcamentoDTOList = new ArrayList<>();
        if (orcamentoList.size() > 0)
            orcamentoList.forEach(orcamento -> orcamentoDTOList.add(OrcamentoMapper.toDto(orcamento)));
        return orcamentoDTOList;
    }

    public static OrcamentoResponse toResponse(Orcamento orcamento, List<ServicoResponse> servicoResponse){
        return OrcamentoResponse.builder()
                .id(orcamento.getId())
                .cliente(ClienteMapper.toResponseSemOrcamento(orcamento.getCliente()))
                .observacoes(orcamento.getObservacoes())
                .servicos(servicoResponse)
                .valorTotal(orcamento.getValorTotal())
                .build();
    }

}
