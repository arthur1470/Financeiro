package br.com.algaworks.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.algaworks.financeiro.model.Lancamento;
import br.com.algaworks.financeiro.repository.LancamentoRepository;
import br.com.algaworks.financeiro.util.Transactional;

@SessionScoped
public class CadastroLancamentos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LancamentoRepository lancamentoRepository;

	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		
		if(lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");					
		}
		
		this.lancamentoRepository.adicionar(lancamento);
	}
}
