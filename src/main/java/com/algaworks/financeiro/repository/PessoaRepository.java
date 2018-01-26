package com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.financeiro.model.Pessoa;

public class PessoaRepository implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public PessoaRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}
	
	public List<Pessoa> todas(){
		TypedQuery<Pessoa> query = manager.createQuery("SELECT p FROM Pessoa p", Pessoa.class);
		return query.getResultList();
	}
}
