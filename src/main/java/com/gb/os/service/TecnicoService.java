package com.gb.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.os.domain.Pessoa;
import com.gb.os.domain.Tecnico;
import com.gb.os.dtos.TecnicoDTO;
import com.gb.os.repositories.PessoaRepository;
import com.gb.os.repositories.TecnicoRepository;
import com.gb.os.service.exceptions.DataIntegratyViolationException;
import com.gb.os.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		if (findbyCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);

		if (findbyCPF(objDTO) != null && findbyCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui Ordens de serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	/*
	 * Busca Técnico pelo CPF 
	 */
	
	private Pessoa findbyCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findbyCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
