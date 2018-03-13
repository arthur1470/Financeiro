package com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.financeiro.model.Lancamento;

public class LancamentoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void guardar(Lancamento lancamento) {
		this.manager.merge(lancamento);
	}

	public void remover(Lancamento lancamento){
		manager.remove(lancamento);
	}
	
	public List<Lancamento> todos(){
		TypedQuery<Lancamento> query = manager.createQuery("FROM Lancamento", Lancamento.class);
		return query.getResultList();
	}

	public List<String> descricoesQueContem(String descricao){
		TypedQuery<String> query = manager.createQuery("SELECT DISTINCT descricao FROM Lancamento " +
				"WHERE UPPER(descricao) LIKE UPPER(:descricao)", String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public Lancamento porId(Long id){
		return manager.find(Lancamento.class, id);
	}
}
