package br.com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.algaworks.financeiro.model.Pessoa;

@SessionScoped
public class PessoaRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> todas() {
		TypedQuery<Pessoa> query = manager.createQuery("SELECT p FROM Pessoa p", Pessoa.class);
		return query.getResultList();
	}
}
