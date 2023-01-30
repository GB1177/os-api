package com.gb.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.os.domain.Cliente;
import com.gb.os.domain.Pessoa;
import com.gb.os.dtos.ClienteDTO;
import com.gb.os.repositories.ClienteRepository;
import com.gb.os.repositories.PessoaRepository;
import com.gb.os.service.exceptions.DataIntegratyViolationException;
import com.gb.os.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> clienteObj = clienteRepository.findById(id);
		return clienteObj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + "Nome: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO ClienteObjDTO) {
		if (findbyCPF(ClienteObjDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return clienteRepository.save(new Cliente(null, ClienteObjDTO.getNome(), ClienteObjDTO.getCpf(), ClienteObjDTO.getTelefone()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO clienteObjDto) {
		Cliente oldClienteObj = findById(id);
		
		if (findbyCPF(clienteObjDto) != null && findbyCPF(clienteObjDto).getId() !=id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldClienteObj.setNome(clienteObjDto.getNome());
		oldClienteObj.setCpf(clienteObjDto.getCpf());
		oldClienteObj.setTelefone(clienteObjDto.getTelefone());
		return clienteRepository.save(oldClienteObj);
	}
	
	public void delete(Integer id) {
		Cliente clienteObj = findById(id);
		
		if(clienteObj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui serviços em aberto, não pode ser deletado!");
		}
		clienteRepository.deleteById(id);
	}
	
	/*
	 * Busca Cliente pelo CPF
	 */

	private Pessoa findbyCPF(ClienteDTO ClienteObjDTO) {
		Pessoa Clienteobj = pessoaRepository.findbyCPF(ClienteObjDTO.getCpf());
		if (Clienteobj != null) {
			return Clienteobj;
		}
		return null;
	}

}
