package com.generationblogpessoal.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import com.generationblogpessoal.model.Postagem;
import com.generationblogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository PostagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(PostagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return PostagemRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	
		 
		
		//select * from tb_postagens where id = id;
	}
		@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(PostagemRepository.findAllBytituloContainingIgnoreCase(titulo));
}
		@PostMapping
		public ResponseEntity<Postagem> postPostagem (@Valid @RequestBody Postagem postagem){
			return ResponseEntity.status(HttpStatus.CREATED).body(PostagemRepository.save(postagem));
		}

		@PutMapping
		public ResponseEntity<Postagem> putPostagem (@Valid @RequestBody Postagem postagem){
			
			return PostagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok().body(PostagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<?> deletePostagem(@PathVariable Long id) {
			
			return PostagemRepository.findById(id)
					.map(resposta -> {
						PostagemRepository.deleteById(id);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.notFound().build());
		}
}
	