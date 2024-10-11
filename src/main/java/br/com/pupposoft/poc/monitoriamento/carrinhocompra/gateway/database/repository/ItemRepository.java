package br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
