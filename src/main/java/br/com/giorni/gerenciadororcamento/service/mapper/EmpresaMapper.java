package br.com.giorni.gerenciadororcamento.service.mapper;

import br.com.giorni.gerenciadororcamento.model.Empresa;
import br.com.giorni.gerenciadororcamento.service.dto.EmpresaDTO;
import br.com.giorni.gerenciadororcamento.service.response.EmpresaResponse;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {

    public static Empresa toEntity(EmpresaDTO empresaDTO) {
        return Empresa
                .builder()
                .id(empresaDTO.getId())
                .nomeFantasia(empresaDTO.getNomeFantasia())
                .numero(empresaDTO.getNumero())
                .logradouro(empresaDTO.getLogradouro())
                .bairro(empresaDTO.getBairro())
                .cep(empresaDTO.getCep())
                .cidade(empresaDTO.getCidade())
                .estado(empresaDTO.getEstado())
                .telefone(empresaDTO.getTelefone())
                .email(empresaDTO.getEmail())
                .build();
    }

    public static EmpresaDTO toDto(Empresa empresa) {

        return EmpresaDTO.builder()
                .id(empresa.getId())
                .nomeFantasia(empresa.getNomeFantasia())
                .numero(empresa.getNumero())
                .bairro(empresa.getBairro())
                .cep(empresa.getCep())
                .cidade(empresa.getCidade())
                .estado(empresa.getEstado())
                .telefone(empresa.getTelefone())
                .email(empresa.getEmail())
                .build();
    }

    public static EmpresaResponse toResponse(Empresa empresa) {

        return EmpresaResponse.builder()
                .id(empresa.getId())
                .nomeFantasia(empresa.getNomeFantasia())
                .bairro(empresa.getBairro())
                .cep(empresa.getCep())
                .cidade(empresa.getCidade())
                .logradouro(empresa.getLogradouro())
                .estado(empresa.getEstado())
                .numero(empresa.getNumero())
                .telefone(empresa.getTelefone())
                .email(empresa.getEmail())
                .build();
    }


}
