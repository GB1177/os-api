package com.gb.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.gb.os.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "O campo NOME é requerido")
	private String nome;

	@CPF
	@NotEmpty(message = "O campo CPF é requerido")
	private String cpf;

	@NotEmpty(message = "O campo TELEFONE é requerido")
	private String telefone;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente clienteObj) {
		super();
		this.id = clienteObj.getId();
		this.nome = clienteObj.getNome();
		this.cpf = clienteObj.getCpf();
		this.telefone = clienteObj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
