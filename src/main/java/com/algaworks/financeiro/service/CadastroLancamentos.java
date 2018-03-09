package com.algaworks.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.LancamentoRepository;
import com.algaworks.financeiro.util.jpa.Transactional;

public class CadastroLancamentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LancamentoRepository lancamentoRepository;

	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		
		if(lancamento.getDataPagamento() != null && 
				lancamento.getDataPagamento().after(new Date())) {
			
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");					
		}
		
		this.lancamentoRepository.adicionar(lancamento);
	}
}
