package br.com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.financeiro.model.Lancamento;
import br.com.algaworks.financeiro.model.Pessoa;
import br.com.algaworks.financeiro.model.TipoLancamento;
import br.com.algaworks.financeiro.repository.PessoaRepository;
import br.com.algaworks.financeiro.service.CadastroLancamentos;
import br.com.algaworks.financeiro.service.NegocioException;

@Named("cadastroLancamentoBean")
@SessionScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private PessoaRepository pessoas;

	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;

	public void prepararCadastro() {
		this.todasPessoas = this.pessoas.todas();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

}
