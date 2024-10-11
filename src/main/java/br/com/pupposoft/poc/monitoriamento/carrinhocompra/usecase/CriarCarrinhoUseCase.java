package br.com.pupposoft.poc.monitoriamento.carrinhocompra.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.monitoriamento.carrinhocompra.domain.CarrinhoCompra;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.domain.Produto;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.CarrinhoGateway;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CriarCarrinhoUseCase {
	
	private ProdutoGateway produtoGateway;
	private CarrinhoGateway carrinhoGateway;

	public Long criar(CarrinhoCompra carrinhoCompra) {
		
		List<Produto> produtos = produtoGateway.obterPorIds(carrinhoCompra.obterIdsProdutos());
		carrinhoCompra.atualizarProdutos(produtos);
		
		return carrinhoGateway.salvar(carrinhoCompra);
	}

}
