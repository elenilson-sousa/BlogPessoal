package com.generationblogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.generationblogpessoal.model.Tema;
import com.generationblogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository TemaRepository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(TemaRepository.findAll());
			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id) {
		return TemaRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	
		 
		
		//select * from tb_postagens where id = id;
	}
		@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByTitulo(@PathVariable String descricao){
		return ResponseEntity.ok(TemaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
}
		@PostMapping
		public ResponseEntity<Tema> postPostagem (@Valid @RequestBody Tema tema){
			return ResponseEntity.status(HttpStatus.CREATED).body(TemaRepository.save(tema));
		}

		@PutMapping
		public ResponseEntity<Tema> putTema (@Valid @RequestBody Tema tema){
			
			return TemaRepository.findById(tema.getId())
				.map(resposta -> ResponseEntity.ok().body(TemaRepository.save(tema)))
				.orElse(ResponseEntity.notFound().build());
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<?> deleteTema(@PathVariable Long id) {
			
			return TemaRepository.findById(id)
					.map(resposta -> {
						TemaRepository.deleteById(id);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.notFound().build());
		}
}