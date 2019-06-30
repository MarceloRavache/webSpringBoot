package com.ufc.br.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Compra;
import com.ufc.br.model.Pessoa;


@Repository
public interface CompraRepository extends JpaRepository<Compra,Long>{
	Compra findByPessoa(Pessoa pessoa);
}
