package com.gb.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gb.os.domain.Cliente;
import com.gb.os.dtos.ClienteDTO;
import com.gb.os.service.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	// localhost:8080/clientes/1

	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		ClienteDTO ClienteObjDTO = new ClienteDTO(service.findById(id));
		return ResponseEntity.ok().body(ClienteObjDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<ClienteDTO> listDTO = service.findAll().stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO ClienteObjDTO) {
		Cliente newClienteObj = service.create(ClienteObjDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClienteObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO ClienteObjDTO){
		ClienteDTO newClienteObj = new ClienteDTO(service.update(id, ClienteObjDTO));
		return ResponseEntity.ok().body(newClienteObj);
	}
	
	/*
	 * Delete Cliente
	 */
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}