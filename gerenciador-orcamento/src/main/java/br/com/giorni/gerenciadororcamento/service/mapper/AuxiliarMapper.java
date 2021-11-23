package br.com.giorni.gerenciadororcamento.service.mapper;

import br.com.giorni.gerenciadororcamento.model.Auxiliar;
import br.com.giorni.gerenciadororcamento.model.Servico;
import br.com.giorni.gerenciadororcamento.service.dto.AuxiliarDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuxiliarMapper {

    public static Auxiliar toEntity(AuxiliarDTO auxiliarDTO){
        if (auxiliarDTO.getServicos() != null){
            List<Servico> servicos = ServicoMapper.listServicoDtoToListServico(auxiliarDTO.getServicos());

            return Auxiliar
                    .builder()
                    .id(auxiliarDTO.getId())
                    .telefone(auxiliarDTO.getTelefone())
                    .nome(auxiliarDTO.getNome())
                    .tipoServico(auxiliarDTO.getTipoServico())
                    .disponbibilidade(auxiliarDTO.isDisponbibilidade())
                    .email(auxiliarDTO.getEmail())
                    .servicos(servicos)
                    .build();
        }
        return Auxiliar
                .builder()
                .id(auxiliarDTO.getId())
                .telefone(auxiliarDTO.getTelefone())
                .nome(auxiliarDTO.getNome())
                .tipoServico(auxiliarDTO.getTipoServico())
                .disponbibilidade(auxiliarDTO.isDisponbibilidade())
                .email(auxiliarDTO.getEmail())
                .build();
    }

    public static AuxiliarDTO toDto(Auxiliar auxiliar){
        return AuxiliarDTO
                .builder()
                .id(auxiliar.getId())
                .telefone(auxiliar.getTelefone())
                .nome(auxiliar.getNome())
                .tipoServico(auxiliar.getTipoServico())
                .disponbibilidade(auxiliar.isDisponbibilidade())
                .email(auxiliar.getEmail())
                .build();
    }

    public static List<Auxiliar> listAuxiliarDtoToListAuxiliar(List<AuxiliarDTO> auxiliarDTOList){
        List<Auxiliar> auxiliarList = new ArrayList<>();
        if (auxiliarDTOList.size() > 0)
            auxiliarDTOList.forEach(auxiliarDTO -> auxiliarList.add(AuxiliarMapper.toEntity(auxiliarDTO)));
        return auxiliarList;
    }

    public static List<AuxiliarDTO> listAuxiliarToListAuxiliarDto(List<Auxiliar> auxiliarList){
        List<AuxiliarDTO> auxiliarDTOList = new ArrayList<>();
        if (auxiliarList.size() > 0) auxiliarList.forEach(auxiliar -> auxiliarDTOList.add(AuxiliarMapper.toDto(auxiliar)));
        return auxiliarDTOList;
    }

}
