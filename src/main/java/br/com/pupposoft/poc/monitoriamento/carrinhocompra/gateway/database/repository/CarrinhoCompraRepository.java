package br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pupposoft.poc.monitoriamento.carrinhocompra.gateway.database.entity.CarrinhoCompraEntity;

public interface CarrinhoCompraRepository extends JpaRepository<CarrinhoCompraEntity, Long> {

}
