package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Compra;
import com.ufc.br.repository.CompraRepository;

@Service
public class CompraService {
	@Autowired
	CompraRepository repoCompra;
	
	public void salvarCompra(Compra compra) {
		repoCompra.save(compra);
	}
	
	public List<Compra> listarCompras(){
		return repoCompra.findAll();
	}
	public Compra buscarCompra(Long id) {
		return repoCompra.getOne(id);
	}
	
}
