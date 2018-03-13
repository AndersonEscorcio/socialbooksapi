package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.domain.comentario;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository LivrosRepository; //Camada de acesso a dados
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	public List<Livro> listar(){
	
		return LivrosRepository.findAll();
	}
	
	public Livro buscar(Long id) {
		
		Livro livro = LivrosRepository.findOne(id);
		if(livro == null) {
			throw new LivroNaoEncontradoException("O livro não pode ser encontrado.");
		}
		return livro;
	}
	
	public Livro salvar(Livro livro) {
		
		livro.setId(null);
		return LivrosRepository.save(livro);
	}
	
	public void deletar(Long id) {
		
		try {
			LivrosRepository.delete(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pode ser encontrado.");
		}
		
	}
	
	public void atualizar(Livro livro) {
		
		verificarExistencia(livro);
		LivrosRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		
		buscar(livro.getId());
	}
	
	public comentario salvarComentario(Long livroId, comentario comentario) {
		
		Livro livro = buscar(livroId);
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return comentariosRepository.save(comentario);
	}
}
