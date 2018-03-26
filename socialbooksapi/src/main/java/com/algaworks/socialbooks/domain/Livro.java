package com.algaworks.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity //Entidade jpa
public class Livro {
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O campo não pode ser vazio.")
	private String nome;
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/mm/yyyy")
	@NotNull(message = "Campo publicação é obrigatório.")
	private Date publicacao;
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Campo editora é obrigatório.")
	private String editora;
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O resumo não pode ser vazio.")
	@Size(max = 1500, message = "O resumo não pode contor mais de 1500 caracteres.")
	private String resumo;
	@JsonInclude(Include.NON_EMPTY)
	//@Transient //Evita que jpa verifique a relação Livro/Comentario
	@OneToMany(mappedBy = "livro")	//O livro poderá ter vários comentários
	private List<comentario> comentarios;
	
	@ManyToOne
	@JoinColumn(name = "AUTOR_ID")
	@JsonInclude(Include.NON_NULL)	
	private Autor autor;
	
	
	public Livro(){}
	
	public Livro(String nome){	
		this.nome = nome;		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getPublicacao() {
		return publicacao;
	}
	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public List<comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	
	
}
