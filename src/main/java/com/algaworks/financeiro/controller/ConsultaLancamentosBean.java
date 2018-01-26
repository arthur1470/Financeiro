package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.LancamentoRepository;
import com.algaworks.financeiro.util.JpaUtil;

@ManagedBean(name = "consultaLancamentosBean")
@RequestScoped
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Lancamento> lancamentos;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		LancamentoRepository repository = new LancamentoRepository(manager);
		this.lancamentos = repository.todos();
		manager.close();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}
