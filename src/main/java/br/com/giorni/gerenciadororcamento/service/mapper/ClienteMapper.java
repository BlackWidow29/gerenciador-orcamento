package br.com.giorni.gerenciadororcamento.service.mapper;

import br.com.giorni.gerenciadororcamento.model.Cliente;
import br.com.giorni.gerenciadororcamento.model.Orcamento;
import br.com.giorni.gerenciadororcamento.service.dto.ClienteDTO;
import br.com.giorni.gerenciadororcamento.service.dto.OrcamentoDTO;
import br.com.giorni.gerenciadororcamento.service.response.ClienteSemOrcamentoResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapper {

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        List<Orcamento> orcamentos = new ArrayList<>();
        if (clienteDTO.getOrcamentos() != null) {
            orcamentos = OrcamentoMapper.listOrcamentoDtoToListOrcamento(clienteDTO.getOrcamentos());

            return Cliente
                    .builder()
                    .id(clienteDTO.getId())
                    .email(clienteDTO.getEmail())
                    .nome(clienteDTO.getNome())
                    .telefone(clienteDTO.getTelefone())
                    .cpfCnpj(clienteDTO.getCpfCnpj())
                    .tipoCliente(clienteDTO.getTipoCliente())
                    .numero(clienteDTO.getNumero())
                    .logradouro(clienteDTO.getLogradouro())
                    .bairro(clienteDTO.getBairro())
                    .cep(clienteDTO.getCep())
                    .cidade(clienteDTO.getCidade())
                    .estado(clienteDTO.getEstado())
                    .orcamentos(orcamentos)
                    .build();
        }

        return Cliente
                .builder()
                .id(clienteDTO.getId())
                .email(clienteDTO.getEmail())
                .nome(clienteDTO.getNome())
                .telefone(clienteDTO.getTelefone())
                .cpfCnpj(clienteDTO.getCpfCnpj())
                .tipoCliente(clienteDTO.getTipoCliente())
                .numero(clienteDTO.getNumero())
                .logradouro(clienteDTO.getLogradouro())
                .bairro(clienteDTO.getBairro())
                .cep(clienteDTO.getCep())
                .cidade(clienteDTO.getCidade())
                .estado(clienteDTO.getEstado())
                .build();
    }

    public static ClienteDTO toDto(Cliente cliente) {
        List<OrcamentoDTO> orcamentos = new ArrayList<>();
        if (cliente.getOrcamentos() != null) {
            orcamentos = OrcamentoMapper.listOrcamentoToListOrcamentoDto(cliente.getOrcamentos());
        }
        return ClienteDTO
                .builder()
                .id(cliente.getId())
                .email(cliente.getEmail())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .cpfCnpj(cliente.getCpfCnpj())
                .tipoCliente(cliente.getTipoCliente())
                .orcamentos(orcamentos)
                .numero(cliente.getNumero())
                .bairro(cliente.getBairro())
                .cep(cliente.getCep())
                .cidade(cliente.getCidade())
                .estado(cliente.getEstado())
                .build();
    }

    public static ClienteSemOrcamentoResponse toResponseSemOrcamento(Cliente cliente) {
        return ClienteSemOrcamentoResponse
                .builder()
                .id(cliente.getId())
                .cpfCnpj(cliente.getCpfCnpj())
                .email(cliente.getEmail())
                .bairro(cliente.getBairro())
                .cep(cliente.getCep())
                .cidade(cliente.getCidade())
                .logradouro(cliente.getLogradouro())
                .estado(cliente.getEstado())
                .numero(cliente.getNumero())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .tipoCliente(cliente.getTipoCliente())
                .build();
    }
}
