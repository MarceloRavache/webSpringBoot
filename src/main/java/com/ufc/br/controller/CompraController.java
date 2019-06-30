                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       package com.ufc.br.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Compra;
import com.ufc.br.model.Finalizar;
import com.ufc.br.model.Item;
import com.ufc.br.model.Pessoa;
import com.ufc.br.service.CompraService;
import com.ufc.br.service.ItemService;
import com.ufc.br.service.PessoaService;

@Controller
@RequestMapping("/compra")
public class CompraController {
	@Autowired
	private CompraService serviceCompra;
	@Autowired
	private PessoaService servicePessoa;
	@Autowired
	private ItemService serviceitem;
	
	@RequestMapping("/final")
	public ModelAndView finalI() {
		ModelAndView mav = new ModelAndView("finalizar");
		mav.addObject("finalizar",new Finalizar());
		return mav;
	}
	
	@RequestMapping(value="/finalizar")
	public ModelAndView finalizar(@Validated Finalizar pess,HttpSession session) {
	
		
//		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		UserDetails usuario = (UserDetails) auth;
		
		Pessoa pessoa = servicePessoa.buscarEmail(pess.getEmail());
		
		ModelAndView mav = new ModelAndView("/compra/listarCompras/"+pessoa.getId());
		
		Compra compra = new Compra();
		compra.setPessoa(pessoa);
		
		compra.setTotal(0);
		serviceCompra.salvarCompra(compra);
		
		List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");
		
		for(Item in : carrinho) {
			in.setCompra(compra);
			serviceitem.salvarItem(in);
		}
		
		double total =(double) session.getAttribute("total");
		
		compra.setTotal(total);
		
		serviceCompra.salvarCompra(compra);
		
		session.removeAttribute("carrinho");
		session.removeAttribute("total");
		
		return mav;
	}
	
	@RequestMapping("/listarCompras/{id}")
	public ModelAndView listarCompras(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("carrinho/compra");
		
		List<Compra> lista = serviceCompra.listarCompras();
		List<Compra> listaPessoa = new ArrayList<>();
		for(Compra x : lista) {
			if(x.getPessoa().getId() == id) {
				listaPessoa.add(x);
			}
		}
		mav.addObject("lista",listaPessoa);
		return mav;
	}
	@RequestMapping("/listarCompra/{id}")
	public ModelAndView listarCompra(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("carrinho/compraDetalhada");
		
		Compra compra = serviceCompra.buscarCompra(id);
		List<Item> itens = compra.getItens();
		mav.addObject("lista",itens);
		
		return mav;
	}
}
