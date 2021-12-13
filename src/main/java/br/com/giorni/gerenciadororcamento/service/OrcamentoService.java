package br.com.giorni.gerenciadororcamento.service;

import br.com.giorni.gerenciadororcamento.model.Orcamento;
import br.com.giorni.gerenciadororcamento.model.Servico;
import br.com.giorni.gerenciadororcamento.repository.OrcamentoRepository;
import br.com.giorni.gerenciadororcamento.repository.ServicoRepository;
import br.com.giorni.gerenciadororcamento.service.dto.OrcamentoDTO;
import br.com.giorni.gerenciadororcamento.service.dto.OrcamentoInputDTO;
import br.com.giorni.gerenciadororcamento.service.dto.ServicoDTO;
import br.com.giorni.gerenciadororcamento.service.mapper.AuxiliarMapper;
import br.com.giorni.gerenciadororcamento.service.mapper.MaterialServicoMapper;
import br.com.giorni.gerenciadororcamento.service.mapper.OrcamentoMapper;
import br.com.giorni.gerenciadororcamento.service.mapper.ServicoMapper;
import br.com.giorni.gerenciadororcamento.service.response.AuxiliarSemServicoResponse;
import br.com.giorni.gerenciadororcamento.service.response.MaterialServicoSemServicoResponse;
import br.com.giorni.gerenciadororcamento.service.response.OrcamentoResponse;
import br.com.giorni.gerenciadororcamento.service.response.ServicoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private OrcamentoMapper orcamentoMapper;

    public void save(OrcamentoDTO orcamentoDTO) {
        try {
            Orcamento orcamento = OrcamentoMapper.toEntity(orcamentoDTO);
            for (Servico servico :
                    orcamento.getServicos()) {
                orcamento.adicionarServico(servico);
                Servico servico2 = servicoRepository.save(servico);
                orcamento = orcamentoRepository.save(orcamento);
                System.out.println(servico2.getOrcamentos());
                System.out.println(orcamento.getServicos());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<OrcamentoResponse> findAll() {
        List<Orcamento> orcamentos = orcamentoRepository.findAll();
        List<OrcamentoResponse> orcamentoResponse = new ArrayList<>();
        List<ServicoResponse> servicoResponse = new ArrayList<>();
        List<MaterialServicoSemServicoResponse> materiais = new ArrayList<>();
        List<AuxiliarSemServicoResponse> auxiliares = new ArrayList<>();

        orcamentos.forEach(orcamento -> {
            if (orcamento.getServicos().size() > 0) {
                orcamento.getServicos().forEach(servico -> {
                    if (servico.getMateriais().size() > 0) {
                        servico.getMateriais().forEach(materialServico -> materiais.add(MaterialServicoMapper.toResponse(materialServico)));
                    }
                    if (servico.getAuxiliares().size() > 0) {
                        servico.getAuxiliares().forEach(auxiliar -> auxiliares.add(AuxiliarMapper.toResponseSemServico(auxiliar)));
                    }
                    servicoResponse.add(ServicoMapper.toResponse(servico, materiais, auxiliares));
                });
            }
            orcamentoResponse.add(OrcamentoMapper.toResponse(orcamento, servicoResponse));
        });
        return orcamentoResponse;
    }

    public Optional<OrcamentoResponse> findById(Long id) {
        Optional<Orcamento> orcamentoOptional = orcamentoRepository.findById(id);
        if (!orcamentoOptional.isPresent()) return Optional.empty();
        List<ServicoResponse> servicoResponse = new ArrayList<>();
        List<MaterialServicoSemServicoResponse> materiais = new ArrayList<>();
        List<AuxiliarSemServicoResponse> auxiliares = new ArrayList<>();
        Orcamento orcamento = orcamentoOptional.get();
        orcamento.getServicos().forEach(servico -> {
            servico.getMateriais().forEach(materialServico -> materiais.add(MaterialServicoMapper.toResponse(materialServico)));
            servico.getAuxiliares().forEach(auxiliar -> auxiliares.add(AuxiliarMapper.toResponseSemServico(auxiliar)));
            servicoResponse.add(ServicoMapper.toResponse(servico, materiais, auxiliares));
        });
        return Optional.of(OrcamentoMapper.toResponse(orcamento, servicoResponse));
    }

    public OrcamentoResponse update(OrcamentoInputDTO orcamentoDTO) {
        Orcamento orcamento = orcamentoMapper.toEntity(orcamentoDTO);
        orcamento = orcamentoRepository.save(orcamento);

        List<ServicoResponse> servicoResponse = new ArrayList<>();
        List<MaterialServicoSemServicoResponse> materiais = new ArrayList<>();
        List<AuxiliarSemServicoResponse> auxiliares = new ArrayList<>();

        orcamento.getServicos().forEach(servico -> {
            servico.getMateriais().forEach(materialServico -> materiais.add(MaterialServicoMapper.toResponse(materialServico)));
            servico.getAuxiliares().forEach(auxiliar -> auxiliares.add(AuxiliarMapper.toResponseSemServico(auxiliar)));
            servicoResponse.add(ServicoMapper.toResponse(servico, materiais, auxiliares));
        });
        return OrcamentoMapper.toResponse(orcamento, servicoResponse);

    }

    public boolean delete(Long id) {
        Optional<Orcamento> orcamento = orcamentoRepository.findById(id);
        if (orcamento.isPresent()) {
            orcamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
