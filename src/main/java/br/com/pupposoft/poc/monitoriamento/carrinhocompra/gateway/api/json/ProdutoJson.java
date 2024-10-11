package br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.api.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ProdutoJson {
	private Long id;
	private String nome;
	private Double preco;
}
