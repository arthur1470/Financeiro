package com.algaworks.financeiro.controller;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.LancamentoRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("consultaLancamentosBean")
@RequestScoped
public class ConsultaLancamentosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoRepository lancamentosRepository;
	
	private List<Lancamento> lancamentos;

	@PostConstruct
	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();		
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}