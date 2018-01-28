package br.com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.algaworks.financeiro.model.Lancamento;

@SessionScoped
public class LancamentoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Inject
	private EntityManager manager;		
	
	public void adicionar(Lancamento lancamento) {
		this.manager.persist(lancamento);
	}
	
	public List<Lancamento> todos(){
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}
}
