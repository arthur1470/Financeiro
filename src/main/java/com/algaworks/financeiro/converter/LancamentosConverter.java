package com.algaworks.financeiro.converter;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.LancamentoRepository;
import com.algaworks.financeiro.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Lancamento.class)
public class LancamentosConverter implements Converter{

    private LancamentoRepository lancamentos = CDIServiceLocator.getBean(LancamentoRepository.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Lancamento retorno = null;
        if(s != null && !"".equals(s)){
            retorno = lancamentos.porId(new Long(s));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o != null){
            Lancamento lancamento = (Lancamento) o;
            return lancamento.getId() == null ? null : lancamento.getId().toString();
        }
        return null;
    }
}
