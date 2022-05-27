package com.generationblogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generationblogpessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long>{

	
}
