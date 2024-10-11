package br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway;

import java.util.List;

import br.com.pupposoft.poc.monitoriamento.carrinhocompra.domain.Produto;

public interface ProdutoGateway {

	List<Produto> obterPorIds(List<Long> obterIdsProdutos);

}
