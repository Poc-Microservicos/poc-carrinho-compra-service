package br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.monitoriamento.carrinhocompra.domain.CarrinhoCompra;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.exception.AcessoRepositorioDadosException;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.CarrinhoGateway;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.entity.CarrinhoCompraEntity;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.entity.ItemEntity;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.entity.ItemId;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.repository.CarrinhoCompraRepository;
import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CarrinhoRepositoryGateway implements CarrinhoGateway {

	private final CarrinhoCompraRepository carrinhoCompraRepository;
	private final ItemRepository itemRepository;
	
	@Override
	@Transactional
	public Long salvar(CarrinhoCompra carrinhoCompra) {
		try {
			
			CarrinhoCompraEntity carrinhoEntity = 
					CarrinhoCompraEntity.builder().idUsuario(carrinhoCompra.getUsuarioId()).build();
			carrinhoCompraRepository.save(carrinhoEntity);
			
			final Long carrinhoId = carrinhoEntity.getId();

			carrinhoCompra.getItens().forEach(i -> {
				ItemId itemId = new ItemId(carrinhoId, i.getProdutoId());
				ItemEntity itemEntity = ItemEntity.builder()
						.id(itemId)
						.quantidade(i.getQuantidade())
						.build();
				
				itemRepository.save(itemEntity);
				
			});
			
			return carrinhoId;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AcessoRepositorioDadosException();
		}
	}
}
