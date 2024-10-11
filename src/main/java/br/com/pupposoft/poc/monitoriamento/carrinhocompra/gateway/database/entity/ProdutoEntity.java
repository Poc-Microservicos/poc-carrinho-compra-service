package br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produto")
public class ProdutoEntity {
	@Id
	private Long id;
	private Double valor;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<ItemEntity> itens;	
}
