package com.ufc.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Item;
import com.ufc.br.model.Pessoa;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
	
}
