package com.senac.guichepratico.resources;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.guichepratico.model.Servico;
import com.senac.guichepratico.repository.ServicoRepository;

@RestController
@RequestMapping(path="/servicos")
public class ServicoResource {
	
	private ServicoRepository servicoRepository;
	
	public ServicoResource(ServicoRepository servicoRepository) {
		super();
		this.servicoRepository = servicoRepository;
	}
	
	@PostMapping
	public ResponseEntity<Servico> save(@RequestBody Servico servico) {
		servicoRepository.save(servico);
		return new ResponseEntity<>(servico, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Servico>> getAll() {
		List<Servico> servicos = (List<Servico>) servicoRepository.findAll();
		return ResponseEntity.ok().body(servicos);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Servico>> deleteById(@PathVariable Long id) {
		try {
			servicoRepository.deleteById(id);
			return new ResponseEntity<Optional<Servico>>(HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Servico>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/")
	public ResponseEntity<Servico> update(@RequestBody Servico servico, Long id) {
	   return servicoRepository.findById(id)
	           .map(record -> {
	               record.setNome(servico.getNome());
	               Servico updated = servicoRepository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
}
