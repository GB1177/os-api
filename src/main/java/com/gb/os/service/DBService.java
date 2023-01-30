package com.gb.os.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.os.domain.Cliente;
import com.gb.os.domain.OS;
import com.gb.os.domain.Tecnico;
import com.gb.os.enums.Prioridade;
import com.gb.os.enums.Status;
import com.gb.os.repositories.ClienteRepository;
import com.gb.os.repositories.OSRepository;
import com.gb.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Bryan Rodrigues", "602.799.550-57", "(88)98888-8888");
		Tecnico t2 = new Tecnico(null, "Gabriel Estefano", "554.974.410-41", "(77)97777-7777");
		Cliente c1 = new Cliente(null, "Lucas Moura", "980.588.490-22", "(99)96666-6666");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
