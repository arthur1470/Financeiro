package com.algaworks.financeiro.controller;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.repository.LancamentoRepository;
import com.algaworks.financeiro.repository.PessoaRepository;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("cadastroLancamentoBean")
@SessionScoped
public class CadastroLancamentoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroLancamentos cadastro;

    @Inject
    private PessoaRepository pessoas;

    @Inject
    private LancamentoRepository lancamentos;

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
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, message);
        }
    }

    public void dataVencimentoAlterada() {
        if (this.lancamento.getDataPagamento() == null) {
            this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
        }
    }

    public List<String> pesquisarDescricoes(String descricao){
        return this.lancamentos.descricoesQueContem(descricao);
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
