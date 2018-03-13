package com.algaworks.financeiro.filter;

import com.algaworks.financeiro.controller.Usuario;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {

    @Inject
    private Usuario usuario;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!usuario.isLogado()
                && !request.getRequestURI().endsWith("/Login.xhtml")
                && !request.getRequestURI().contains("/javax.faces.resource/")) {

            response.sendRedirect(request.getContextPath() + "/Login.xhtml");
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
