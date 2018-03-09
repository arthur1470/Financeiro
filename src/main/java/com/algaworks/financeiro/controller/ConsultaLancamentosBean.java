package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.LancamentoRepository;

@Named("consultaLancamentosBean")
@RequestScoped
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoRepository lancamentosRepository;
	
	private List<Lancamento> lancamentos;

	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();		
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}
