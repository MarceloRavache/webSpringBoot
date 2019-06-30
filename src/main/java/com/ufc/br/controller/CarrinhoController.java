package com.ufc.br.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Item;
import com.ufc.br.model.Prato;
import com.ufc.br.service.ItemService;
import com.ufc.br.service.PratoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	@Autowired
	PratoService servicePrato;
	@Autowired
	ItemService service;

	@RequestMapping("/add/{id}")
	public ModelAndView addCarrinho(@PathVariable("id") long id, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/carrinho/listar");

		if(session.getAttribute("carrinho") == null){
			List<Item> carrinho = new ArrayList<Item>();
			
			Item item = service.fazerItem(id);
			
			double totalItem = item.getPrato().getPreco()*item.getQuant();
		
			item.setValor(item.getPrato().getPreco());
			
			carrinho.add(item);
			
			session.setAttribute("carrinho", carrinho);
			session.setAttribute("total", item.getPrato().getPreco());
			
		}else {
			List<Item> carrinho =(List<Item>) session.getAttribute("carrinho");
			
			int i = this.tem(id, carrinho);
			
			if(i == -1) {
				Item item = service.fazerItem(id);
				
				item.setQuant(1);
				
				item.setValor(item.getPrato().getPreco());
				
				carrinho.add(item);
				
			}else {
				int quant = carrinho.get(i).getQuant();
				
				carrinho.get(i).setQuant(quant + 1);
				
				
				double preco = carrinho.get(i).getPrato().getPreco();
				double totalItem = preco*quant;
				
				carrinho.get(i).setValor(totalItem);
				
				
			}
			
			double aux = 0;
			for(Item in: carrinho) {
				aux = aux + in.getQuant() * in.getPrato().getPreco();
			}
			
			session.setAttribute("carrinho", carrinho);
			session.setAttribute("total", aux);
		}
		
		return mv;
	}
	
	
	
	
	private int tem(long codigo,List<Item> lista) {
		for(int i = 0;i<lista.size();i++) {
			if(lista.get(i).getPrato().getId() == codigo) {
				return i;
			}
		}
		return -1;
	}
	
	@RequestMapping("/remove/{id}")
	public ModelAndView removeCarrinho(@PathVariable("id") long id, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/carrinho/listar");
		
		List<Item> carrinho = (List<Item>) session.getAttribute("carrinho");
		
		int i = tem(id,carrinho);
		
		if(carrinho.get(i).getQuant() > 1) {
			carrinho.get(i).setQuant(carrinho.get(i).getQuant()-1);
		}else {
			carrinho.remove(i);
		}
		if(carrinho.size() == 0) {
			session.setAttribute("carrinho", null);
			session.setAttribute("total", null);
		}else {
			session.setAttribute("carrinho", carrinho);
			
			double aux = 0;
			for(Item in: carrinho) {
				aux = aux + in.getQuant() * in.getPrato().getPreco();
			}
			
			session.setAttribute("total", aux);
		}
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar(HttpSession session) {
		ModelAndView mav = new ModelAndView("carrinho/listar");
		List<Item> itens = (List<Item>) session.getAttribute("carrinho");
		mav.addObject("listaCarrinho",itens);

		return mav;
	}
	
}
