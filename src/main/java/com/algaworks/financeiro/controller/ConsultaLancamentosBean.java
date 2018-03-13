package com.algaworks.financeiro.controller;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.LancamentoRepository;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

	@Inject
	private CadastroLancamentos cadastro;
	
	private List<Lancamento> lancamentos;

	private Lancamento lancamentoSelecionado;

	@PostConstruct
	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();		
	}


	public void excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();

			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));

		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}

	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}