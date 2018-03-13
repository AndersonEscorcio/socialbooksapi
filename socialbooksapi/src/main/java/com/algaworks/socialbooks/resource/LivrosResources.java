package com.algaworks.socialbooks.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.domain.comentario;
import com.algaworks.socialbooks.services.LivrosService;

@RestController /*Defino que é um recurso para o Spring*/
@RequestMapping(value = "/livros") //Mapeando a partir daqui...
public class LivrosResources {
	
	@Autowired
	//private LivrosRepository LivrosRepository; //Camada de acesso a dados
	private LivrosService LivrosService;
	
	@RequestMapping(method = RequestMethod.GET)/*Mapeando URI para um determinado method(GET)*/
	public ResponseEntity<List<Livro>> Listar() {
		
		//return ResponseEntity.status(HttpStatus.OK).body(LivrosRepository.findAll());
		return ResponseEntity.status(HttpStatus.OK).body(LivrosService.listar());
		/*
		Livro l1 = new Livro("Rest aplicado");
		Livro l2 = new Livro("Git passo-a-passo");
		*/
		/* Teste - Anderson
		Livro l1 = new Livro();
		Livro l2 = new Livro();
		
		l1.setNome("Rest aplicado by Anderson");
		l2.setNome("Git passo-a-passo by Anderson");
		*/
		/*
		Livro[] livros = {l1,l2};
				
		return Arrays.asList(livros); 
		*/		
	}
	//Criando método "criação"
	@RequestMapping(method = RequestMethod.POST)/*Mapeando URI para um determinado method(POST)*/
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
	
		//livro = LivrosRepository.save(livro);
		livro = LivrosService.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)/*Mapeando URI para um determinado method(GET)*/
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		/*
		Livro livro = LivrosRepository.findOne(id);
		
		if(livro == null) {
			return ResponseEntity.notFound().build(); //Não encontrou (404)
		}
		*/
		Livro livro = LivrosService.buscar(id);		
		return ResponseEntity.status(HttpStatus.OK).body(livro); //Encontrou(200 OK)
		
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)/*Mapeando URI para um determinado method(DELETE)*/
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		LivrosService.deletar(id);
		return ResponseEntity.noContent().build();
				
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)/*Mapeando URI para um determinado method(PUT)*/
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro,@PathVariable("id") Long id) {
		livro.setId(id); //aponto livro que será atualizado
		LivrosService.atualizar(livro);		
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") long livroId, @RequestBody comentario comentario) {
		LivrosService.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri(); 
		
		return ResponseEntity.created(uri).build();
				
	}
	
}
