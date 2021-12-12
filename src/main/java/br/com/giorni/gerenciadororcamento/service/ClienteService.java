package br.com.giorni.gerenciadororcamento.service;

import br.com.giorni.gerenciadororcamento.model.Cliente;
import br.com.giorni.gerenciadororcamento.repository.ClienteRepository;
import br.com.giorni.gerenciadororcamento.repository.EnderecoRepository;
import br.com.giorni.gerenciadororcamento.service.dto.ClienteDTO;
import br.com.giorni.gerenciadororcamento.service.mapper.ClienteMapper;
import br.com.giorni.gerenciadororcamento.service.response.ClienteSemOrcamentoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    public Cliente save(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public List<ClienteSemOrcamentoResponse> findAll(){
        List<Cliente> clienteList = clienteRepository.findAll();
        return clienteList.stream()
                .map(ClienteMapper::toResponseSemOrcamento)
                .collect(Collectors.toList());
    }

    public Optional<ClienteSemOrcamentoResponse> findById(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()){
            ClienteSemOrcamentoResponse clienteSemOrcamentoResponse = ClienteMapper.toResponseSemOrcamento(cliente.get());
            return Optional.of(clienteSemOrcamentoResponse);
        }
        return Optional.empty();
    }

    public Cliente update(ClienteDTO clienteDTO){
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public boolean delete(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
