package br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import br.com.pupposoft.poc.monitoriamento.carrinhocompra.domain.Produto;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.exception.AcessoProdutoServiceException;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.ProdutoGateway;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.api.json.ProdutoJson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProdutoApiServiceGateway implements ProdutoGateway {

	@Value("${poc.monitoramento.service.produto.base-url}")
	private String produtoBaseUrl;
	
	@Override
	public List<Produto> obterPorIds(List<Long> idsProdutos) {

		try {
			RestClient restClient = RestClient.create();
			final String url = produtoBaseUrl + "/poc/monitoramento/produto/v1/produtos/";
			
			
			return idsProdutos.stream()
			.map(id -> url + id)
			.map(urlFinal -> restClient.get()
						.uri(urlFinal)
						.retrieve()
						.body(ProdutoJson.class))
			.map(this::mapJsonToDomain)
			.toList();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoProdutoServiceException();
		}
		
	}

	private Produto mapJsonToDomain(ProdutoJson produtoJson) {
		return new Produto(produtoJson.getId(), BigDecimal.valueOf(produtoJson.getPreco()));
	}
	
}
