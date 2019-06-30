package com.ufc.br.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Pessoa;
import com.ufc.br.model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato,Long>{
	List<Prato> findByStatus(int status);
}
