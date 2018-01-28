package br.com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.financeiro.model.Lancamento;
import br.com.algaworks.financeiro.repository.LancamentoRepository;

@Named("consultaLancamentosBean")
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoRepository repository;
	
	private List<Lancamento> lancamentos;

	public void consultar() {
		this.lancamentos = repository.todos();		
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}
