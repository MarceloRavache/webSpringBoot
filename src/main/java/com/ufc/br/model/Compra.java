package com.ufc.br.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCompra;
	
	private double total;
	
	@ManyToOne
	private Pessoa pessoa;
	
	@OneToMany(mappedBy="compra", cascade= CascadeType.ALL)
	private List<Item> itens;

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	

}
